package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.CommonPack;
import com.sandrew.bury.model.SessionPO;
import org.junit.Test;

import java.util.Date;

public class InsertTestMySql
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
			// 2.0版新增
			com.sandrew.bury.model.Session se = new com.sandrew.bury.model.Session();
			se.setSessionId("3");
			se.setSession("qweqweqweqwe");
			se.setCreateBy(new CommonPack<Integer>(-1));
			se.setCreateDate(new Date());
			session.insert(se);

			// 1.0版新增
			SessionPO sessionPO = new SessionPO();
			sessionPO.setSessionId("4");
			sessionPO.setSession("没 vi 去年我去问");
			sessionPO.setCreateBy(-1);
			sessionPO.setCreateDate(new Date());
			session.insert(sessionPO);

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
