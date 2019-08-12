/***************************************************************************************************
 * <pre>
 * FILE : XmlPOConfigParser.java
 * CLASS : XmlPOConfigParser
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
 * 		  |2010-4-15| SuMMeR| Created |
 * DESCRIPTION:
 * </pre>
 **************************************************************************************************/
/**
 * $Id: XmlPOConfigParser.java,v 0.1 2010-4-15 下午02:33:48 SuMMeR Exp $
 */

package com.sandrew.bury.generator;

import com.sandrew.bury.configuration.ConfigConnection;
import com.sandrew.bury.exception.ParseException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function :
 * 
 * @author : SuMMeR CreateDate : 2010-4-15
 * @version :
 */
public class XmlPOConfigParser implements POConfigParser
{

	/*
	 * (non-Javadoc)
	 * @see com.autosys.po3.configure.POConfigParser#parser(java.io.File)
	 */
	public POConfig parser()
	{
		return parserConfig();
	}

	/**
	 * 获取配置文件，调用getConfigureFile(String fileName) Function : LastUpdate :
	 * 2010-4-15
	 * 
	 * @return
	 * @throws DocumentException
	 */
	private Document getConfigureFile() throws DocumentException
	{
		return getConfigureFile("POConf.xml");
	}

	/**
	 * 获取配置文件 Function : LastUpdate : 2010-4-15
	 * 
	 * @param fileName
	 * @return
	 * @throws DocumentException
	 */
	private Document getConfigureFile(String fileName) throws DocumentException
	{
		// 获取配置文件的输入流
		InputStream is = ClassLoader.getSystemResourceAsStream(fileName);
		// File file = new File();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(is);
		return doc;
	}

	/**
	 * Function : 解析XML配置文件 LastUpdate : 2010-4-16
	 * 
	 * @return
	 */
	private POConfig parserConfig()
	{
		POConfig conf = new POConfig();
		try
		{
			Document doc = getConfigureFile();

			/*
			 * 获取,解析数据库连接信息
			 */

			List<Node> dbConfigList = doc.selectNodes("/PO_CONFIG/DB_CONNECTION");
			// 获取数据库类型、连接信息的结点
			Node dbConfigNode = dbConfigList.get(0);
			// 获取数据库类型
			Element dbConfigElement = asElement(dbConfigNode);
			String dataBaseType = dbConfigElement.attribute("TYPE").getValue();
			conf.setDbType(dataBaseType);
			// 获取数据库连接信息
			List<Element> dbConfigProList = dbConfigElement.elements();
			Map<String, String> tempMap = new HashMap<String, String>();
			ConfigConnection connection = null;
			for (Element element : dbConfigProList)
			{
				String proName = element.attribute("NAME").getText();
				String proValue = element.getText();
				if (null == proName || "".equals(proName))
				{
					throw new RuntimeException("解析失败, 数据库连接配置不允许为空");
				}
				tempMap.put(proName, proValue);
				conf.setDbUrl(tempMap.get("DB_URL"));
				conf.setDbUser(tempMap.get("DB_USER"));
				conf.setDbPassword(tempMap.get("DB_PASSWORD"));
			}
			/*
			 * 获取,解析要生成PO的表信息
			 */
			// 解析父类PO的包名(由于PO3框架PO的包名已经确定,因此不再配置文件里配置,也无须解析)
			//conf.setFatherPackage("com.autosys.po3.bean");

			// 解析生成PO的绝对路径
			List<Node> genPORealPathList = doc.selectNodes("/PO_CONFIG/GEN_PO_PATH");
			Node genPORealPathNode = genPORealPathList.get(0);
			Element genPORealPathElement = asElement(genPORealPathNode);
			String genPORealPath = genPORealPathElement.attribute("NAME").getValue();
			conf.setGenPoPath(genPORealPath);
			// 解析生成PO的包信息
			List<Node> poPackageList = doc.selectNodes("/PO_CONFIG/TABLES");
			Node poPackageNode = poPackageList.get(0);
			Element poPackageElement = asElement(poPackageNode);
			String poPackage = poPackageElement.attribute("PACKAGE").getValue();
			conf.setPackageName(poPackage);

			// 解析要生成PO的表
			List<Node> poList = doc.selectNodes("/PO_CONFIG/TABLES/TABLE");
			for (int i = 0; i < poList.size(); i++)
			{
				TabConfig table = new TabConfig();
				String tabName = asElement(poList.get(i)).attribute("TAB_NAME").getText();
				String poName = asElement(poList.get(i)).attribute("PO_NAME").getText();
				table.setTableName(tabName);
				table.setPoName(poName);
				List<Node> idNodes = poList.get(i).selectNodes("ID");
				for (Node idNode : idNodes)
				{
					String pkName = asElement(idNode).attribute("COL_NAME").getText();
					String increaseVal = asElement(idNode).attribute("INCREASE").getText();
					if (!("true".equalsIgnoreCase(increaseVal) || "false".equalsIgnoreCase(increaseVal)))
					{
						throw new ParseException("INCREASE must value of true or false");
					}
					boolean isIncrease = Boolean.valueOf(increaseVal);
					table.addPk(pkName, isIncrease);
				}
				conf.addTable(table);
			}
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		return conf;
	}

	/**
	 * 
	 * Function    : 将Node转型为Element
	 * LastUpdate  : 2010-4-16
	 * @return
	 */
	private Element asElement(Node node)
	{
		return (Element) node;
	}

}
