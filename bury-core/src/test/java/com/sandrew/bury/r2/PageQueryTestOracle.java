package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.model.TryxxPO;
import org.junit.Test;

public class PageQueryTestOracle
{

	@Test
	public void test()
	{
		String configFile = "bury-config.xml";
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configFile);
		Session session = factory.openSession("ds002");
		try
		{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TRYXX");
			PageResult<TryxxPO> result = session.pageQuery(sql.toString(), null, new POCallBack(TryxxPO.class), 10, 2);
			System.out.println(result);
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
