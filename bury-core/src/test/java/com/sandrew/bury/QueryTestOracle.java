package com.sandrew.bury;

import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.model.TmUserPO;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTestOracle
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
            session.select("select * from tt_test_ora", null, new DAOCallback<TmUserPO>()
            {
                @Override
                public TmUserPO wrapper(ResultSet rs, int index) throws SQLException
                {
                    TmUserPO user = new TmUserPO();
                    System.out.println(rs.getInt("ID"));
                    System.out.println(rs.getString("NAME"));
                    System.out.println(rs.getDate("BIRTHDAY"));
                    System.out.println(rs.getBlob("FILE_DATA"));
                    user.setUserId(rs.getInt("ID"));
                    return user;
                }
            });
//            TestOraPO po = new TestOraPO();
//            po.setId(10000043);
//            List<TestOraPO> list = session.select(po);
//            System.out.println("count ----------------" + list.size());
//
//            System.out.println("code :" + list.get(0).getFileData().length);



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
