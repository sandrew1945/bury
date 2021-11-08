package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.common.POTypes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CallProcedureOracle
{

	@Test
	public void test()
	{
		String configFile = "bury-config.xml";
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configFile);
		Session session = factory.openSession("ds002");
		try
		{
			List<Object> ins = new ArrayList<>();
			List<Integer> outs = new ArrayList<>();
			ins.add("www");
			outs.add(POTypes.VARCHAR);
			outs.add(POTypes.VARCHAR);
			List<Object> list = session.callProcedure("TESTPRO", ins, outs);
			System.out.println(list);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
	}


}
