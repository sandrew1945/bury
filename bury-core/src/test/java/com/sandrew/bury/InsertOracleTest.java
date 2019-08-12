package com.sandrew.bury;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.model.TestOraPO;
import org.junit.Test;

import java.util.Date;

public class InsertOracleTest
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

				Session session = factory.openSession("ds002");
				try
				{
					TestOraPO ora = new TestOraPO();
					ora.setId(session.getIntegerPK("SEQ_TEST_ORA"));
					ora.setName("DS003");
					ora.setBirthday(new Date());
					ora.setZipCode("1300001");
					int count = session.insert(ora);
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
