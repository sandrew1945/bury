/***************************************************************************************************
 * <pre>
* FILE : OracleSqlCreatorImpl.java
* CLASS : OracleSqlCreatorImpl
*
* AUTHOR : SuMMeR
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2010-8-31| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/

package com.sandrew.bury.sql;


import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.Pack;
import com.sandrew.bury.common.POMapping;
import com.sandrew.bury.util.BuryConstants;
import com.sandrew.bury.util.POUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-8-31
 * @version    :
 */
public class DefaultSqlCreatorImpl implements SqlCreator
{

	final static Logger logger = LoggerFactory.getLogger(DefaultSqlCreatorImpl.class);

	/* (non-Javadoc)
	 * @see com.autosys.po3.sql.SqlCreator#deleteCreator()
	 */
	public String deleteCreator(POMapping mapping, PO po)
	{
		StringBuilder sql = new StringBuilder();
		// 生成delete语句前缀
		sql.append(deletePrefixCreator(mapping.getTableName()));
		// 生成delete条件
		try
		{
			sql.append(whereCreator(mapping, po));
		}
		catch (Exception e)
		{
			throw new RuntimeException("create the SQL of delete error!", e);
		}
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.autosys.po3.sql.SqlCreator#insertCreator()
	 */
	public String insertCreator(POMapping mapping, PO po)
	{
		StringBuilder sql = new StringBuilder();
		// 生成insert语句前缀
		sql.append(insertPrefixCreator(mapping.getTableName()));
		// 生成insert语句后缀
		sql.append(insertSuffixCreator(mapping, po));
		return sql.toString();
	}

	@Override
	public String insertAllCreator(POMapping mapping, PO po)
	{
		StringBuilder sql = new StringBuilder();
		// 生成insert语句前缀
		sql.append(insertPrefixCreator(mapping.getTableName()));
		// 生成insert语句后缀
		sql.append(insertAllSuffixCreator(mapping, po));
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.autosys.po3.sql.SqlCreator#selectCreator()
	 */
	public String selectCreator(POMapping mapping, PO po)
	{
		StringBuilder sql = new StringBuilder();

		try
		{
			// 生成select语句前缀
			sql.append(selectPrefixCreator(mapping.getTableName()));
			// 生成select语句后缀
			sql.append(whereCreator(mapping, po));
		}
		catch (Exception e)
		{
			throw new RuntimeException("create the SQL of select error!", e);
		}
		return sql.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.autosys.po3.sql.SqlCreator#selectCreatorForOrder(com.autosys.po3.common.POMapping, com.autosys.po3.bean.PO, java.lang.String, java.lang.String[])
	 */
	public String selectCreatorForOrder(POMapping mapping, PO po, String order, String... colName)
	{
		StringBuilder sql = new StringBuilder();
		sql.append(selectCreator(mapping, po));
		sql.append(orderCreator(order, colName));
		return sql.toString();
	}

	/* (non-Javadoc)
	 * @see com.autosys.po3.sql.SqlCreator#updateCreator()
	 */
	public String updateCreator(POMapping mapping, PO cond, PO value)
	{
		StringBuilder sql = new StringBuilder();
		try
		{
			// 生成update语句前缀
			sql.append(updatePrefixCreator(mapping.getTableName()));
			// 生成update语句后缀
			sql.append(updateSuffixCreator(mapping, value));
			// 生成where条件
			sql.append(whereCreator(mapping, cond));
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			throw new RuntimeException("create the SQL of update error!", e);
		}
		return sql.toString();
	}

	@Override
	public String getProdOrFuncSql(String functionName, List<Object> ins, List<Integer> outs, boolean isProcedure)
	{
		try
		{
			StringBuilder sB = new StringBuilder();
			if (isProcedure)
			{
				// 拼装存储过程的SQL
				sB.append(BuryConstants.PROCEDURE_PREFIX).append(functionName);
				sB.append(getProdOrFuncParameters(ins, outs));
			}
			else
			{
				// 拼装Function的SQL
				sB.append(BuryConstants.FUNCTION_PREFIX).append(functionName);
				sB.append(getProdOrFuncParameters(ins, outs));
			}
			sB.append(BuryConstants.PROD_FUNC_SUFFIX);
			return sB.toString();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			throw new RuntimeException("create the SQL of Prod Or Func error!", e);
		}
	}

	/**
	 * 
	 * Function    : 生成ORACLE形式的SELECT查询前缀
	 * LastUpdate  : 2010-8-31
	 * @param tabName
	 * @return
	 */
	private String selectPrefixCreator(String tabName)
	{
		return "SELECT * FROM " + tabName;
	}

	/**
	 * 
	 * Function    : 生成ORACLE形式的WHERE条件
	 * LastUpdate  : 2010-9-1
	 * @param mapping
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private String whereCreator(POMapping mapping, PO po) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" WHERE 1 = 1");
		// 循环遍历PO的属性，生成AND条件
		for (int i = 0; i < mapping.getColSize(); i++)
		{
			// 如果PO此属性不为null，则添加AND添件
			// 获取此属性字段的get方法
			Object value = POUtil.reflectGetByField(po, mapping.getPropertyName(i));
			if (value instanceof Pack)
			{
				Pack pack = (Pack) value;
				if (null != pack)
				{
					if (null != pack.getValue())
					{
						sql.append(" AND ").append(pack.toSql(mapping.getColName(i)));
					}
					else
					{
						sql.append(" AND ").append(pack.toNullSql(mapping.getColName(i)));
					}
				}

			}
			else
			{
				// 兼容1.0版本
				if (null != value)
				{
					sql.append(" AND ").append(mapping.getColName(i)).append(" = ?");

				}
			}
		}
		return sql.toString();
	}

	/**
	 * 
	 * Function    : 生成ORACLE形式的ORDER BY
	 * LastUpdate  : 2010-9-1
	 * @param colName
	 * @param order
	 * @return
	 */
	private String orderCreator(String order, String... colName)
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" ORDER BY ");
		for (int i = 0; i < colName.length; i++)
		{
			if (i != 0)
			{
				sql.append(",");
			}
			sql.append(colName[i]);
		}
		sql.append(" " + order);
		return sql.toString();
	}

	/**
	 * 
	 * Function    : 生成ORACLE形式的UPDATE前缀
	 * LastUpdate  : 2010-9-1
	 * @param tabName
	 * @return
	 */
	private String updatePrefixCreator(String tabName)
	{
		return "UPDATE " + tabName;
	}

	/**
	 * 
	 * Function    : 生成通用格式的INSERT前缀
	 * LastUpdate  : 2010-9-1
	 * @param tabName
	 * @return
	 */
	private String insertPrefixCreator(String tabName)
	{
		return "INSERT INTO " + tabName;
	}

	/**
	 * 
	 * Function    : 生成通用格式的INSERT后缀
	 * LastUpdate  : 2010-9-1
	 * @param mapping
	 * @return
	 */
	private String insertSuffixCreator(POMapping mapping, PO po)
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" (");
		Class<?> clz = po.getClass();
		Field[] fields = clz.getDeclaredFields();
		int insertValCount = 0;
		for(int i = 0;i<fields.length;i++)
		{
			// 如果PO此属性不为null，则添加
			// 获取此属性字段的get方法

			Object value = POUtil.reflectGetByField(po, mapping.getPropertyName(i));
			if (value instanceof Pack)
			{
				Pack pack = (Pack) value;
				if (null != pack)
				{
					sql.append(mapping.getColName(i)).append(",");
					insertValCount++;
				}
			}
			else
			{
				// 兼容1.0版本
				if (null != value)
				{
					sql.append(mapping.getColName(i)).append(",");
					insertValCount++;
				}
			}
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") VALUES (");
		for (int i = 0; i < insertValCount; i++)
		{
			if (i != 0)
			{
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		return sql.toString();
	}

	/**
	 *	生成全部字段插入的sql
	 * @param mapping
	 * @param po
	 * @return
	 */
	private String insertAllSuffixCreator(POMapping mapping, PO po)
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" (");
		Class<?> clz = po.getClass();
		Field[] fields = clz.getDeclaredFields();
		int insertValCount = 0;
		for(int i = 0;i<fields.length;i++)
		{
			// 全部字段都添加
			sql.append(mapping.getColName(i)).append(",");
			insertValCount++;
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") VALUES (");
		for (int i = 0; i < insertValCount; i++)
		{
			if (i != 0)
			{
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		return sql.toString();
	}

	/**
	 * 
	 * Function    : 生成UPDATE后缀
	 * LastUpdate  : 2010-9-1
	 * @param mapping
	 * @return
	 */
	private String updateSuffixCreator(POMapping mapping, PO po)
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" SET ");
		Field[] fields = po.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
		{
			// 调用此字段的get方法
//			Object value = POUtil.invokeGetMethodByField(po, fields[i].getName());
//			if (null != value)
//			{
//				sql.append(mapping.getColName(i)).append(" = ?,");
//			}

			Object value = POUtil.reflectGetByField(po, mapping.getPropertyName(i));
			if (value instanceof Pack)
			{
				Pack pack = (Pack) value;
				if (null != pack)
				{
					sql.append(mapping.getColName(i)).append(" = ?,");
				}
			}
			else
			{
				// 兼容1.0版本
				if (null != value)
				{
					sql.append(mapping.getColName(i)).append(" = ?,");
				}
			}



		}
		// 删除最后一个","
		String sqlStr = sql.substring(0, sql.length() - 1);
		return sqlStr;
	}

	/**
	 * 
	 * Function    : 生成ORACLE形式的DELETE后缀
	 * LastUpdate  : 2010-9-1
	 * @param tabName
	 * @return
	 */
	private String deletePrefixCreator(String tabName)
	{
		return "DELETE FROM " + tabName;
	}

	/**
	 *
	 * Function    : 拼装Procedure或Function SQL的参数部分
	 * LastUpdate  : 2010-6-12
	 * @param ins
	 * @param outs
	 * @return
	 */
	private String getProdOrFuncParameters(List<Object> ins, List<Integer> outs)
	{
		StringBuilder sB = new StringBuilder();
		sB.append(BuryConstants.PROD_FUNC_PARAMS_PREFIX);
		if (null != ins && ins.size() > 0)
		{
			for (int i = 0; i < ins.size(); i++)
			{
				if (hasParametersHead(sB.toString()))
				{
					sB.append(",?");
				}
				else
				{
					sB.append("?");
				}
			}
		}
		if (null != outs && outs.size() > 0)
		{
			for (int i = 0; i < outs.size(); i++)
			{
				if (hasParametersHead(sB.toString()))
				{
					sB.append(",?");
				}
				else
				{
					sB.append("?");
				}
			}
		}
		sB.append(BuryConstants.PROD_FUNC_PARAMS_SUFFIX);
		return sB.toString();
	}

	private boolean hasParametersHead(String paramters)
	{
		return paramters.startsWith(BuryConstants.PROD_FUNC_PARAMS_PREFIX + "?");
	}

	/**
	 * Function    : 
	 * LastUpdate  : 2010-8-31
	 * @param args
	 */
	public static void main(String[] args)
	{
		
	}

}
