package com.sandrew.bury.r2;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.CommonPack;
import com.sandrew.bury.model.SessionPO;
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
			se.setCreateBy(new CommonPack(null));
//			se.setCreateDate(new Date());
//			session.insert(se);


			List<com.sandrew.bury.model.Session> list = session.select(se);
			list.stream().forEach(item -> {
				System.out.println(item);
			});

			SessionPO sessionPO = new SessionPO();
			sessionPO.setSessionId("1");
			sessionPO.setSession("qweqweqweqwe");

			List<SessionPO> list2 = session.select(sessionPO);
			list2.stream().forEach(item -> {
				System.out.println(item);
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
