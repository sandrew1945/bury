package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import org.junit.Test;

public class QueryTestById
{

	@Test
	public void test()
	{
		String configFile = "bury-config.xml";
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configFile);
		Session session = factory.openSession();
		try
		{
			// 2.0版本查询
			com.sandrew.bury.model.Session se = new com.sandrew.bury.model.Session("1");
			com.sandrew.bury.model.Session value = session.selectById(se);
			System.out.println(value);
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
