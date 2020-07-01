package com.sandrew.bury;

import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.model.UserTag;
import org.junit.Test;

import java.util.List;

public class QueryTestForCumtomModel
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
			StringBuilder sql = new StringBuilder();
			sql.append("select u.user_id, u.user_code, u.user_name, 'ausdnqwe' as tag from tm_user u");
			List<UserTag> list = session.select(sql.toString(), null, new POCallBack(UserTag.class));
			for (UserTag userTag : list)
			{
				System.out.println(userTag);
			}


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
