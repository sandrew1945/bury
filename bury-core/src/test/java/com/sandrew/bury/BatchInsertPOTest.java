package com.sandrew.bury;

import com.sandrew.bury.exception.POException;
import com.sandrew.bury.model.UORelTab;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BatchInsertPOTest
{

    @Test
    public void test()
    {
        try
        {
            String configFile = "bury-config.xml";
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(configFile);

            Session session = factory.openSession();
            try
            {
                List<UORelTab> list = new ArrayList<>();
                for (int i = 0; i < 10; i++)
                {
                    UORelTab uoRelTab = new UORelTab();
                    uoRelTab.setItemId(i + 100);
                    uoRelTab.setUoId(i+1);
                    list.add(uoRelTab);
                }
                int[] count = session.insert(list);
                System.out.println("count ----------------" + count + " id:" + count.length);
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
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
