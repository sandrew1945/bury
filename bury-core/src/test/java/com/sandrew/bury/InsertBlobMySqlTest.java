package com.sandrew.bury;

import com.sandrew.bury.exception.POException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class InsertBlobMySqlTest
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
                File file = new File("/Users/summer/Downloads/N3-admin-master.zip");
                FileInputStream fis = new FileInputStream(file);
                List<Object> param = new ArrayList<Object>();
                param.add(3);
                param.add(fis);
                int i = session.insertForLob("INSERT INTO tt_test (sd, file) VALUES (?, ?)", param);

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
