package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.EqualPack;
import com.sandrew.bury.bean.GreaterEqualPack;
import com.sandrew.bury.bean.GreaterLessEqualPack;
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
			// 2.0版本查询
			com.sandrew.bury.model.Session se = new com.sandrew.bury.model.Session();
			se.setSessionId("1");
			se.setSession("qweqweqweqwe");
			se.setCreateBy(new EqualPack(null));
//			se.setCreateDate(new Date());
//			session.insert(se);
			List<com.sandrew.bury.model.Session> list = session.select(se);
			list.stream().forEach(item -> {
				System.out.println(item);
			});

			// 1.0版查询
			SessionPO sessionPO = new SessionPO();
			sessionPO.setSessionId("1");
			sessionPO.setSession("qweqweqweqwe");

			List<SessionPO> list2 = session.select(sessionPO);
			list2.stream().forEach(item -> {
				System.out.println(item);
			});

			// 2.0版大于/大于等于查询
			com.sandrew.bury.model.Session se2 = new com.sandrew.bury.model.Session();
			se2.setSessionId("1");
			se2.setSession("qweqweqweqwe");
			se2.setCreateBy(new GreaterEqualPack<Integer>(-2));
			List<com.sandrew.bury.model.Session> list3 = session.select(se2);
			list3.stream().forEach(item -> {
				System.out.println(item.getSessionId().getValue());
				System.out.println(item.getSession().getValue());
			});

			// 2.0between查询
			com.sandrew.bury.model.Session se3 = new com.sandrew.bury.model.Session();
			se3.setSessionId(new GreaterLessEqualPack<String>("1", "3"));
			se3.setSession("ndiniannsadasdasd");
			List<com.sandrew.bury.model.Session> list4 = session.select(se3);
			list4.stream().forEach(item -> {
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
