package com.sandrew.bury.r1;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.exception.POException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateBlobOracleTest
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
                File file = new File("/Users/summer/Downloads/N3-admin-master.zip");
                FileInputStream fis = new FileInputStream(file);
                List<Object> param = new ArrayList<Object>();
                param.add(fis);
                param.add(10000021);
                int i = session.updateForLob("UPDATE TT_TEST_ORA SET FILE_DATA = ? WHERE ID = ?", param);

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
