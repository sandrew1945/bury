package com.sandrew.bury;

import com.sandrew.bury.exception.POException;
import com.sandrew.bury.util.BatchParameter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BatchInsertSqlTest
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
                String sql = "insert nesc_uo_rel_tab (item_id, uo_id, rel_type) values (?, ?, 20051001)";
                List<BatchParameter> parameterList = new ArrayList<>();

                for (int i = 0; i < 10; i++)
                {
                    BatchParameter parameter = new BatchParameter();
                    parameter.add(i + 99);
                    parameter.add(i + 99);
                    parameterList.add(parameter);
                }
                int[] count = session.insertForBatch(sql, parameterList);
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
