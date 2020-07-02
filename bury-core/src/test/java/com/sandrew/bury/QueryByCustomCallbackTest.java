package com.sandrew.bury;

import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.model.UORelExt;
import org.junit.Test;

import java.util.List;

public class QueryByCustomCallbackTest
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
			StringBuilder sql = new StringBuilder("select *, 'abc' as `name` from nesc_uo_rel_tab");
			List<UORelExt> list = session.select(sql.toString(), null, new POCallBack(UORelExt.class));
			for (UORelExt uoRelExt : list)
			{
				System.out.println(uoRelExt.getName());
				System.out.println(uoRelExt.getId());
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
