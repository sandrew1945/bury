package com.sandrew.bury.r2;

import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.bean.EqualPack;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.model.UserR2PO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchInsertUserTest
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
                List<UserR2PO> list = new ArrayList<>();
                for (int i = 0; i < 10; i++)
                {
                    UserR2PO user = new UserR2PO();
                    user.setUserCode("code" + i);
                    if (i != 2)
                    {
                        user.setUserName(new EqualPack<String>(null));
                        user.setBirthday(new EqualPack<Date>(null));
                        user.setUserStatus(new EqualPack<Integer>(null));
                    }
                    else
                    {
                        user.setUserName("name" + i);
                        user.setBirthday(new Date());
                        user.setUserStatus(10011001);
                    }
                    list.add(user);
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
