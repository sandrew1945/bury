package com.sandrew.bury;

import com.sandrew.bury.configuration.ConfigParser;
import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.configuration.XMLConfigParser;

import java.io.InputStream;

/**
 * Created by summer on 2019/5/29.
 */
public class SqlSessionFactoryBuilder
{

    public SqlSessionFactory build(String resource)
    {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
        return build(is);
    }

    public SqlSessionFactory build(InputStream is)
    {
        ConfigParser parser = new XMLConfigParser();
        Configuration configuration = parser.parse(is);
        DefaultSqlSessionFactory factory = new DefaultSqlSessionFactory(configuration);
        return factory;
    }
}
