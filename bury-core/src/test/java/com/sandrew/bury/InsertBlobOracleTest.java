package com.sandrew.bury;

import com.sandrew.bury.exception.POException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class InsertBlobOracleTest
{

    @Test
    public void test()
    {
        try
        {
            String configFile = "bury-config.xml";
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(configFile);
            Session session = factory.openSession("ds002");
            try
            {
                File file = new File("/Users/summer/Downloads/jresplus-t2sdk-1.2.9.zip");
                FileInputStream fis = new FileInputStream(file);
                List<Object> param = new ArrayList<Object>();
                param.add(session.getIntegerPK("SEQ_TEST_ORA"));
                param.add("FOR BLOB2");
                param.add(fis);
                int i = session.insertForLob("INSERT INTO TT_TEST_ORA (ID, NAME, FILE_DATA) VALUES (?, ?, ?)", param);

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
