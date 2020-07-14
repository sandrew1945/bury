package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.model.SessionExt;
import org.junit.Test;

import java.util.List;

public class QueryTestForExtModel
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
			StringBuilder sql = new StringBuilder("select *, 'abc' as ext from tt_session");

			List<SessionExt> list = session.select(sql.toString(), null, new POCallBack(SessionExt.class));
			list.stream().forEach(item -> {
				System.out.println("id :" + item.getSessionId().getValue() + " session:" + item.getSession().getValue() + " ext:" + item.getExt() + " createdate : " + item.getCreateDate().getValue());
			});

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
