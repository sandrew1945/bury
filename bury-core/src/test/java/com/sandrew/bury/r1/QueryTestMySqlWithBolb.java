package com.sandrew.bury.r1;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.model.TestPO;
import org.junit.Test;

import java.util.List;

public class QueryTestMySqlWithBolb
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
            po.setId(6);
            List<TestPO> list = session.select(po);
            System.out.println("count ----------------" + list.size());
            System.out.println("code :" + list.get(0).getFile().length);
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
