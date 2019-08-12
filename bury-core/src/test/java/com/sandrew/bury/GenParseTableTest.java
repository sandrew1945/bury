package com.sandrew.bury;

import com.sandrew.bury.generator.*;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class GenParseTableTest
{

	@Test
	public void test()
	{
		try
		{
			String configFile = "POConf.xml";
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
			POConfigParser parser = new XmlPOConfigParser();
			POConfig config = parser.parser();
			System.out.println(config);
			DefaultTableParser tableParser = new DefaultTableParser();
			List<Table> tables = tableParser.parserTable(config);
			System.out.println(tables);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
