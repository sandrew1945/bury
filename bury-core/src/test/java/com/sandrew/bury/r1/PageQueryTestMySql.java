package com.sandrew.bury.r1;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.model.UserTabExt;
import com.sandrew.bury.util.Parameters;
import org.junit.Test;

public class PageQueryTestMySql
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
			sql.append("select ut.*, ot.org_id , ot.org_name from nesc_user_tab ut left join nesc_org_tab ot on ut.`department_id` = ot.`org_id`\n");
			sql.append("where ut.is_delete = ?\n");
			Parameters parameters = new Parameters(new Integer(10031002));
			PageResult<UserTabExt> result = session.pageQuery(sql.toString(), parameters.getParams(), new POCallBack(UserTabExt.class), 10, 1);
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
