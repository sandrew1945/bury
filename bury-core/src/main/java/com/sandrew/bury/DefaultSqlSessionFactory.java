package com.sandrew.bury;

import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.datasource.DataSourceWrapper;
import com.sandrew.bury.exception.ParseException;
import com.sandrew.bury.executor.BaseExecutor;
import com.sandrew.bury.executor.Executor;
import com.sandrew.bury.executor.OracleExecutor;
import com.sandrew.bury.lob.extractor.C3P0NativeJdbcExtractor;
import com.sandrew.bury.lob.extractor.DruidNativeJdbcExtractor;
import com.sandrew.bury.lob.extractor.Jdbc4NativeJdbcExtractor;
import com.sandrew.bury.lob.extractor.NativeJdbcExtractor;
import com.sandrew.bury.transaction.Transaction;
import com.sandrew.bury.transaction.TransactionFactory;
import com.sandrew.bury.util.BuryConstants;

/**
 * Created by summer on 2019/5/28.
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory
{
    protected Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration)
    {
        this.configuration = configuration;
    }

    @Override
    public Session openSession()
    {
        Transaction transaction = TransactionFactory.newTransaction(configuration.getDataSource(BuryConstants.DEFAULT_DATASOURCE_KEY));

        Executor executor = getExecutor(getDatabaseType(configuration.getDataSource(BuryConstants.DEFAULT_DATASOURCE_KEY)), transaction, getNativeJdbcExtrator(configuration, BuryConstants.DEFAULT_DATASOURCE_KEY));
        DefaultSession session = getSession(executor, BuryConstants.DEFAULT_DATASOURCE_KEY);
        return session;
    }

    @Override
    public Session openSession(String datasourceKey)
    {
        if (null == datasourceKey || "".equals(datasourceKey))
        {
            return openSession();
        }
        Transaction transaction = TransactionFactory.newTransaction(configuration.getDataSource(datasourceKey));
        Executor executor = getExecutor(getDatabaseType(configuration.getDataSource(datasourceKey)), transaction, getNativeJdbcExtrator(configuration, datasourceKey));
        DefaultSession session = getSession(executor, datasourceKey);
        return session;
    }

    /**
     *  根据数据库类型获取Session
     * @param executor
     * @return
     */
    protected DefaultSession getSession(Executor executor, String datasoureKey)
    {
        DefaultSession session;
        if (BuryConstants.DATABASE_TPYE_ORACLE.equals(getDatabaseType(configuration.getDataSource(datasoureKey))))
        {
            session = new OracleSession(configuration, executor);
        }
        else if (BuryConstants.DATABASE_TPYE_MYSQL.equals(getDatabaseType(configuration.getDataSource(datasoureKey))))
        {
            session = new MySqlSession(configuration, executor);
        }
        else
        {
            session = new MSSqlSession(configuration, executor);
        }
        return session;
    }

    /**
     *  获取数据库类型
     * @param dataSource
     * @return
     */
    protected String getDatabaseType(DataSourceWrapper dataSource)
    {
        return dataSource.getDatabaseType();
    }

    /**
     *  根据连接池类型获取NativeJdbcExtractor
     * @param configuration
     * @param datasourceKey
     * @return
     */
    protected NativeJdbcExtractor getNativeJdbcExtrator(Configuration configuration, String datasourceKey)
    {
        String dataSourceName = configuration.getDataSource(datasourceKey).getDataSourceName();
        if (BuryConstants.DATASOURCE_DIRECT.equals(dataSourceName))
        {
            return new Jdbc4NativeJdbcExtractor();
        }
        else if (BuryConstants.DATASOURCE_C3P0.equals(dataSourceName))
        {
            return new C3P0NativeJdbcExtractor();
        }
        else if (BuryConstants.DATASOURCE_DRUID.equals(dataSourceName))
        {
            return new DruidNativeJdbcExtractor();
        }
        else
        {
            throw new ParseException("Connection pool :" + dataSourceName + " is not support!");
        }
    }

    protected Executor getExecutor(String databaseType, Transaction transaction, NativeJdbcExtractor nativeJdbcExtractor)
    {
        if (BuryConstants.DATABASE_TPYE_ORACLE.equals(databaseType))
        {
            return new OracleExecutor(databaseType, transaction, nativeJdbcExtractor);
        }
        else
        {
            return new BaseExecutor(databaseType, transaction, nativeJdbcExtractor);
        }
    }
}
