package com.sandrew.bury;

import com.sandrew.bury.generator.POConfig;
import com.sandrew.bury.generator.POConfigParser;
import com.sandrew.bury.generator.XmlPOConfigParser;
import org.junit.Test;

import java.io.InputStream;

public class GenParsePOConfTest
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
