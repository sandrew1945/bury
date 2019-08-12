/***************************************************************************************************
 * <pre>
* FILE : POGenerator.java
* CLASS : POGenerator
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
* 		  |2010-4-20| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/
/**
 * $Id: POGenerator.java,v 0.1 2010-4-20 下午11:12:52 SuMMeR Exp $
 */

package com.sandrew.bury.util;

import com.sandrew.bury.generator.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-4-20
 * @version    :
 */
public class POGenerator
{
	final static Logger logger = LoggerFactory.getLogger(POGenerator.class);
	//private string 
	private POConfig conf;

	/**
	 * 在static块中 初始化 VelocityEngine 类，这里面的参数我还不是很清楚，请阅读者 在评论中告知，
	 */
	static VelocityEngine ve;

	static
	{
		try
		{
			// 获取文件模板根路径
			ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "classpath:/templates/");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			ve.init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	static String CONTENT_ENCODING = "UTF-8";


	/**
	 *  生成PO类
	 * @throws Exception
     */

	public void gen(String poConfigPath) throws Exception
	{
		String configFile = poConfigPath == null ? "POConf.xml" : poConfigPath;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
		POConfigParser parser = new XmlPOConfigParser();
		POConfig config = parser.parser();

		DefaultTableParser tableParser = new DefaultTableParser();
		List<Table> tables = tableParser.parserTable(config);


		Template template = ve.getTemplate("templates/POTemplate.vm", CONTENT_ENCODING);
		// 处理生成绝对路径,如果以文件分隔符结尾那么不处理,否则增加文件分隔符
		String genPOPath = config.getGenPoPath().endsWith(File.separator) ? config.getGenPoPath() : (config.getGenPoPath() + File.separator);
		for (Table table : tables)
		{

			File file = new File(genPOPath + table.getPoName() + ".java");  /* 定义输出替换文件的目录 */
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, CONTENT_ENCODING));
			VelocityContext context = new VelocityContext();  /* 定义输出类中需要替换的对象 */
			context.put("className", table.getPoName()); /* 替换类名*/
			context.put("package", config.getPackageName()); /* 替换报名 */
			context.put("tableName", table.getTableName());
			context.put("hasDate", false);
			List<Field> fields = table.getFields();
			List<Entity> entities = new ArrayList<Entity>();
			for (Field field : fields)
			{
				entities.add(new Entity(field.getFieldType().getSimpleName(), field.getFieldName(), field.getAttributeName(), field.isPK(), field.isAutoIncrement()));
				if (field.getFieldType().equals(Date.class))
				{
					context.put("hasDate", true);
				}
				if (field.getFieldType().equals(BigDecimal.class))
				{
					context.put("hasBigDecimal", true);
				}
				if (field.getFieldType().equals(Blob.class))
				{
					context.put("hasBlob", true);
				}
				if (field.getFieldType().equals(Clob.class))
				{
					context.put("hasClob", true);
				}
			}
			context.put("columnDatas", entities);  /* 替换属性名称,和setter，getter */
			template.merge(context, writer);  /* 执行替换方法*/
			writer.flush();
			writer.close();
			fos.close();
			logger.info("Generate model " + table.getPoName() + " to " + file.getPath());
		}
		logger.info("Generated all models!");
	}
}
