package com.sandrew.bury.r1;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.model.SessionPO;
import org.junit.Test;

public class QueryByIdTest
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
			SessionPO po = new SessionPO();
			po.setSessionId("1");
			SessionPO value = session.selectById(po);
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
