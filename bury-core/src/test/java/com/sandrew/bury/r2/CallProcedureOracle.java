package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.common.POTypes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CallProcedureOracle
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
			// 调用Procedure
			List<Object> ins = new ArrayList<>();
			List<Integer> outs = new ArrayList<>();
			ins.add("www");
			outs.add(POTypes.VARCHAR);
			outs.add(POTypes.VARCHAR);
			List<Object> list = session.callProcedure("TESTPRO", ins, outs);
			System.out.println(list);

			// 调用返回一个CURSOR的Procedure
			List<String> list2 = session.callProcedure("TESTPROCUR", null, (rs, index) -> {
				System.out.println(rs.getString("ID"));
				System.out.println(rs.getString("NAME"));
				System.out.println(rs.getString("DESCP"));
				return rs.getString("ID");
			});
			System.out.println(list2);
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
