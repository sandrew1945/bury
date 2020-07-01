package com.sandrew.bury.generator;


import com.sandrew.bury.util.POUtil;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-4-23
 * @version    :
 */
public class DefaultTableParser
{


	// 查询表的SQL模板1
	private String queryTemplate = "select * from {0}";

	/*
	 * 	返回所有要生成PO的表的表名、字段及属性
	 * 	return : List<HashMap<String, List<HashMap<String, Object>>>>
	 * 			 List<HashMap<表名, List<HashMap<字段名, 类型>>>>
	 * (non-Javadoc)
	 * @see com.autosys.po3.table.TableParser#parserTable(com.autosys.po3.configure.Configure)
	 */
	public List<Table> parserTable(POConfig conf) throws Exception
	{
		List<Table> list = new ArrayList<Table>();
		// 获取数据库连接
		Class.forName(POUtil.getDriverClass(conf.getDbType()));
		Connection conn = DriverManager.getConnection(conf.getDbUrl(), conf.getDbUser(), conf.getDbPassword());
		// 根据配置文件获取要生成PO的表名集合
		List<TabConfig> tables = conf.getTabConfigList();
		if (null == tables || tables.size() == 0)
		{
			throw new Exception("POConf.xml配置错误,请正确配置要生成PO的数据库表名");
		}

		for (TabConfig tabConfig : tables)
		{
			Table table = new Table();
			table.setTableName(tabConfig.getTableName());
			String poName = tabConfig.getPoName() == null ? getPONameByTableName(tabConfig.getTableName()):tabConfig.getPoName();
			table.setPoName(poName);
			table.setFields(getPOInfo(conn, tabConfig.getTableName()));
			list.add(table);
		}

		return list;
	}

	/**
	 * 
	 * Function    : 根据表名获取表的字端名称及类型
	 * LastUpdate  : 2010-4-26
	 * @param conn
	 * @return	   : List<HashMap<String, Object>>
	 * 				 Key : 字段名称
	 * 				 Value ：类型
	 * @throws Exception 
	 */
	private List<Field> getPOInfo(Connection conn, String tableName) throws Exception
	{
		List<Field> list = new ArrayList<Field>();
		// 获取查询SQL
		String querySql = MessageFormat.format(queryTemplate, tableName);
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(querySql);
		ResultSetMetaData meta = rs.getMetaData();
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet dbMeteRs = dbMeta.getPrimaryKeys(conn.getCatalog(), null, tableName);
		List<String> pkList = new ArrayList<String>();
		while (dbMeteRs.next())
		{
			pkList.add(dbMeteRs.getString("COLUMN_NAME"));
		}
		// 循环遍历表，获取所有的字段及类型
		for (int i = 1; i <= meta.getColumnCount(); i++)
		{
			Field field = new Field();
			String colName = meta.getColumnName(i);
			int colType = meta.getColumnType(i);
			int precision = 0;
			int scale = 0;
			if(colType != Types.BLOB && colType != Types.CLOB && colType != Types.LONGVARBINARY)
			{
				precision = meta.getPrecision(i);
				scale = meta.getScale(i);
			}
			DefaultClassMaker maker = new DefaultClassMaker();
			// 根据字段的属性获取字段类型
			Class<?> cls = maker.getJavaType(colName, colType, precision, scale, meta.isSigned(i));
			field.setFieldName(colName);
			field.setAttributeName(POUtil.getAttributeNameByFieldName(colName));
			field.setFieldType(cls);
			field.setAutoIncrement(meta.isAutoIncrement(i));
			if (pkList.contains(colName))
			{
				field.setPK(true);
			}
			list.add(field);
		}
		return list;
	}

	/**
	 *  
	 * Function    : 根据表名生成PO类名
	 * LastUpdate  : 2010-4-30
	 * @param tableName
	 * @return
	 */
	private String getPONameByTableName(String tableName)
	{
		// 将表名变为小写
		tableName = tableName.toLowerCase();
		// 按"_"分割,然后首字母大写
		String[] parts = tableName.split("_");
		StringBuilder PO = new StringBuilder();
		for (int i = 0; i < parts.length; i++)
		{
			String temp = POUtil.toUpCaseOfInitial(parts[i]);
			PO.append(temp);
		}
		PO.append("PO");
		return PO.toString();
	}

	/*
	private String getAttributeNameByFieldName(String fieldName)
	{
		// 将字段名变为小写
		fieldName = fieldName.toLowerCase();
		// 按"_"分割,然后首字母大写
		String[] parts = fieldName.split("_");
		StringBuilder attribute = new StringBuilder();
		for (int i = 0; i < parts.length; i++)
		{
			// 属性名首字母小写
			String temp = null;
			if (i != 0)
			{
				temp = POUtil.toUpCaseOfInitial(parts[i]);
			}
			else
			{
				temp = parts[i];
			}
			attribute.append(temp);
		}
		return attribute.toString();
	}
	*/
	/**
	 * Function    : 
	 * LastUpdate  : 2010-4-23
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*
		ConfigureFileParser confParser = new XmlParser();
		Configure conf = confParser.parser();
		OracleTableParser tableParser = new OracleTableParser();
		try
		{
			List list = tableParser.parserTable(conf);
			System.out.println(list);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		DefaultTableParser tableParser = new DefaultTableParser();
		System.out.println(POUtil.getAttributeNameByFieldName("VEHICLE"));

		//parser.getPOInfo();
	}

}
