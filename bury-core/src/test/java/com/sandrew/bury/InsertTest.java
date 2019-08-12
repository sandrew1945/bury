package com.sandrew.bury;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.model.TmUserPO;
import org.junit.Test;

import java.util.Date;

public class InsertTest
{

	@Test
	public void test()
	{
		try
		{
			String configFile = "bury-config.xml";
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(configFile);
			for (int j = 0 ; j < 10 ; j ++)
			{

				Session session = factory.openSession();
				try
				{
					//int i = session.insert("INSERT INTO tt_session (session_id, session, create_by, create_date) VALUES ('" + j + "', '111123', -1, SYSDATE())", null);
					TmUserPO user = new TmUserPO();
					user.setUserCode("code"+j);
					user.setUserName("name"+j);
					user.setBirthday(new Date());
					user.setCreateDate(new Date());
					int count = session.insert(user);
					System.out.println("count ----------------" + count + " id:" + user.getUserId());
					session.commit();
				}
				catch (POException e)
				{
					e.printStackTrace();
				}
				finally
				{
					session.close();
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


}
