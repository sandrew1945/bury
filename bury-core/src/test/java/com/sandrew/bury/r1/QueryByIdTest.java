package com.sandrew.bury.r1;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.model.TestPO;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class QueryByIdTest
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
			TestPO po = new TestPO();
			po.setId(1);
			po.setSd(1);
			po.setName("qweqwe");
			po.setBirthday(new Date());
			po.setBlance(new BigDecimal(123.12));
			TestPO value = session.selectById(po);
			System.out.println(value);

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
