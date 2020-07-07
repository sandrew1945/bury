package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.model.TtTest;
import org.junit.Test;

public class QueryTestWithBlobMySql
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
			TtTest ttTest = session.selectById(new TtTest(1));
			System.out.println(ttTest);
			System.out.println(ttTest.getFile().getValue().length);
			//session.commit();
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
