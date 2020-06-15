package com.sandrew.bury.r1;

import com.sandrew.bury.util.POGenerator;
import org.junit.Test;

public class POGeneratorTest
{

	@Test
	public void test()
	{
		try
		{
			String configFile = "POConf.xml";
			POGenerator poGenerator = new POGenerator();
			poGenerator.gen("POConf.xml");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
