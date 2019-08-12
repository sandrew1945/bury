package com.sandrew.bury;

import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.model.TmUserPO;
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
			sql.append("SELECT  tu.user_id ,\n");
			sql.append("	tu.user_code ,\n");
			sql.append("	tu.user_name ,\n");
			sql.append("	tu.sex ,\n");
			sql.append("	tu.user_status ,\n");
			sql.append("	tu.phone ,\n");
			sql.append("	tu.mobile ,\n");
			sql.append("	tu.email ,\n");
			sql.append("	tr.role_code ,\n");
			sql.append("	group_concat(tr.role_name) AS role_name\n");
			sql.append("FROM tm_user tu\n");
			sql.append("LEFT JOIN tr_user_role tur ON tu.user_id = tur.user_id\n");
			sql.append("LEFT JOIN(\n");
			sql.append("	SELECT tm_role.role_id ,\n");
			sql.append("	       tm_role.role_code ,\n");
			sql.append("	       tm_role.role_name\n");
			sql.append("	FROM tm_role\n");
			sql.append("	WHERE tm_role.is_delete = 10031002\n");
			sql.append("	AND tm_role.role_status = 10011001\n");
			sql.append(") tr ON tr.role_id = tur.role_id\n");
			sql.append("WHERE	1 = 1\n");
			sql.append("AND tu.is_delete = 10031002\n");
			sql.append("GROUP BY user_code, is_delete");
			PageResult<TmUserPO> result = session.pageQuery(sql.toString(), null, new POCallBack(TmUserPO.class), 10, 1);
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
