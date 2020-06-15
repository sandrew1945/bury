package com.sandrew.bury.r2;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class QueryTestMySql
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

			com.sandrew.bury.model.Session se = new com.sandrew.bury.model.Session();
			se.setSessionId("1");
			se.setSession("qweqweqweqwe");
//			se.setCreateDate(new Date());
//			session.insert(se);


			List<com.sandrew.bury.model.Session> list = session.select(se);
			list.stream().forEach(item -> {
				System.out.println(item.getSessionId().getValue());
				System.out.println(item.getSession().getValue());
			});
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
