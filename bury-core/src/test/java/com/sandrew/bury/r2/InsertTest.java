package com.sandrew.bury.r2;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.model.TtTest;
import org.junit.Test;

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
			for (int j = 0 ; j < 3 ; j ++)
			{

				Session session = factory.openSession();
				try
				{
					TtTest ttTest = new TtTest();
					ttTest.setSd(10 + j);
					ttTest.setHasInfo(false);
					session.insert(ttTest);
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
