package com.sandrew.bury;

import com.sandrew.bury.configuration.ConfigParser;
import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.configuration.XMLConfigParser;
import org.junit.Test;

import java.io.InputStream;

public class ParseConfigTest
{

	@Test
	public void test()
	{
		try
		{
			String configFile = "bury-config.xml";
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
			ConfigParser parser = new XMLConfigParser();
			Configuration configuration = parser.parse(is);
			System.out.println(configuration);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
