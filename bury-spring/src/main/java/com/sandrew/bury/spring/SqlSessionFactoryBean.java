package com.sandrew.bury.spring;

import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.datasource.DataSourceWrapper;
import com.sandrew.bury.spring.transation.SpringSqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;

/**
 * Created by summer on 2019/7/25.
 */
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent>
{
    private SqlSessionFactory sqlSessionFactory;

    //private String transactionManager;

    private Map<String, DataSourceWrapper> dataSources;

    public void onApplicationEvent(ApplicationEvent applicationEvent)
    {

    }

    public SqlSessionFactory getObject() throws Exception
    {
        Configuration configuration = new Configuration();
        //configuration.setTransactionManager(this.transactionManager);
        if (null != this.dataSources)
        {
            for (String key : this.dataSources.keySet())
            {
                configuration.addDataSource(key, this.dataSources.get(key));
            }
        }
        SpringSqlSessionFactory factory = new SpringSqlSessionFactory(configuration);
        return factory;
    }

    public Class<?> getObjectType()
    {
        return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();
    }

    public boolean isSingleton()
    {
        return true;
    }

    public void afterPropertiesSet() throws Exception
    {

    }

    public Map<String, DataSourceWrapper> getDataSources()
    {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSourceWrapper> dataSources)
    {
        this.dataSources = dataSources;
    }
}
