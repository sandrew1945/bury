package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.GreaterLessEqualPack;
import com.sandrew.bury.bean.GreaterPack;
import org.junit.Test;

public class UpdateTestMySql
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
			// 2.0版更新
			com.sandrew.bury.model.Session cond = new com.sandrew.bury.model.Session();
			cond.setSessionId(new GreaterLessEqualPack<String>("1", "3"));

			com.sandrew.bury.model.Session value = new com.sandrew.bury.model.Session();
			value.setSession("99QMWIN9123-NASIQWE7-123ANSDU99");
			value.setUpdateBy(new GreaterPack<Integer>(1));
			session.update(cond, value);

			// 1.0版更新
//			SessionPO cond2 = new SessionPO();
//			cond2.setSessionId("1");
//
//			SessionPO value2 = new SessionPO();
//			value2.setSession("ndiniannsadasdasd");
//			session.update(cond2, value2);

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
