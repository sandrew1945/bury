package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.EqualPack;
import com.sandrew.bury.bean.GreaterEqualPack;
import com.sandrew.bury.model.SessionPO;
import org.junit.Test;

public class DeleteTestMySql
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
			// 2.0版删除
			com.sandrew.bury.model.Session se = new com.sandrew.bury.model.Session();
			se.setSessionId(new GreaterEqualPack<String>("10"));
			se.setCreateBy(new EqualPack(null));
			session.delete(se);

			// 1.0版删除
			SessionPO sessionPO = new SessionPO();
			sessionPO.setSessionId("qoweiqweuyqwemqweni");
			sessionPO.setSession("111123");
			session.delete(sessionPO);

			session.commit();
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
