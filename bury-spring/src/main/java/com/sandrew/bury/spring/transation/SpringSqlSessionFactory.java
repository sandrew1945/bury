package com.sandrew.bury.spring.transation;

import com.sandrew.bury.DefaultSession;
import com.sandrew.bury.DefaultSqlSessionFactory;
import com.sandrew.bury.Session;
import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.executor.Executor;
import com.sandrew.bury.transaction.Transaction;
import com.sandrew.bury.util.BuryConstants;

/**
 * Created by summer on 2019/8/6.
 */
public class SpringSqlSessionFactory extends DefaultSqlSessionFactory
{

    public SpringSqlSessionFactory(Configuration configuration)
    {
        super(configuration);
    }

    public Session openSession()
    {
        Transaction transaction = SpringTransactionFactory.newTransaction(configuration.getDataSource(BuryConstants.DEFAULT_DATASOURCE_KEY));

        Executor executor = getExecutor(getDatabaseType(configuration.getDataSource(BuryConstants.DEFAULT_DATASOURCE_KEY)), transaction, getNativeJdbcExtrator(configuration, BuryConstants.DEFAULT_DATASOURCE_KEY));
        DefaultSession session = getSession(executor, BuryConstants.DEFAULT_DATASOURCE_KEY);
        return session;
    }

    public Session openSession(String datasourceKey)
    {
        if (null == datasourceKey || "".equals(datasourceKey))
        {
            return openSession();
        }
        Transaction transaction = SpringTransactionFactory.newTransaction(configuration.getDataSource(datasourceKey));
        Executor executor = getExecutor(getDatabaseType(configuration.getDataSource(datasourceKey)), transaction, getNativeJdbcExtrator(configuration, datasourceKey));
        DefaultSession session = getSession(executor, datasourceKey);
        return session;
    }
}
