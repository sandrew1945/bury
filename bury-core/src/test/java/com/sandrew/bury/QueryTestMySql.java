package com.sandrew.bury;

import com.sandrew.bury.model.TmUserPO;
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

			for (int j = 0 ; j < 100 ; j ++)
			{
				//int i = session.insert("INSERT INTO tt_session (session_id, session, create_by, create_date) VALUES ('" + j + "', '111123', -1, SYSDATE())", null);
				TmUserPO user = new TmUserPO();
				user.setUserCode("code" + j);
				List<TmUserPO> userlist = session.select(user);
				System.out.println("count ----------------" + userlist.size());
				System.out.println("code :" + userlist.get(0).getUserCode());
			}

			TmUserPO po = new TmUserPO();
			po.setUserId(11362);
			List<TmUserPO> userlist = session.select(po);
			System.out.println("count ----------------" + userlist.size());
			System.out.println("code :" + userlist.get(0).getUserCode());

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
