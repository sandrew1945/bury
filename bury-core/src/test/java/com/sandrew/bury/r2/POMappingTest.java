package com.sandrew.bury.r2;

import com.sandrew.bury.common.POMapping;
import com.sandrew.bury.model.Session;
import org.junit.Test;

public class POMappingTest
{

	@Test
	public void test()
	{
		try
		{
			Session po = new Session();
			POMapping mapping = new POMapping(po);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
