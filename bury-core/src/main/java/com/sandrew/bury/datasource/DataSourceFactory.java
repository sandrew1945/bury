package com.sandrew.bury.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sandrew.bury.util.POUtil;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.sandrew.bury.configuration.ConfigConnection;
import com.sandrew.bury.util.BuryConstants;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 *  数据源包装类
 *
 * Created by summer on 2019/5/13.
 */
public class DataSourceFactory
{
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DataSourceFactory.class);

    public static DataSourceWrapper newDataSourceWrapper(ConfigConnection connection)
    {
        DataSourceWrapper dataSourceWrapper = new DataSourceWrapper();
        dataSourceWrapper.setDatabaseType(connection.getDatabaseType());
        dataSourceWrapper.setDataSourceName(connection.getDatasource());
        DataSource dataSource = getDataSource(connection);
        dataSourceWrapper.setDataSource(dataSource);
        dataSourceWrapper.setAutocommit(connection.isAutocommit());
        return dataSourceWrapper;
    }

    private static DataSource getDataSource(ConfigConnection configConnection)
    {
        try
        {
            switch (configConnection.getDatasource())
            {
                case BuryConstants.DATASOURCE_C3P0:
                {
                    ComboPooledDataSource dataSource = new ComboPooledDataSource();
                    dataSource.setDriverClass(POUtil.getDriverClass(configConnection.getDatabaseType()));
                    dataSource.setJdbcUrl(configConnection.getUrl());
                    dataSource.setUser(configConnection.getUsername());
                    dataSource.setPassword(configConnection.getPassword());
                    dataSource.setInitialPoolSize(configConnection.getInitialPoolSize().intValue());
                    dataSource.setMaxPoolSize(configConnection.getMaxPoolSize().intValue());
                    dataSource.setMinPoolSize(configConnection.getMinPoolSize().intValue());
                    dataSource.setMaxConnectionAge(0);
                    return dataSource;
                }
                case BuryConstants.DATASOURCE_DRUID:
                {
                    DruidDataSource dataSource = new DruidDataSource();

                    dataSource.setUrl(configConnection.getUrl());
                    dataSource.setUsername(configConnection.getUsername());
                    dataSource.setPassword(configConnection.getPassword());
                    dataSource.setDriverClassName(POUtil.getDriverClass(configConnection.getDatabaseType()));
                    dataSource.setMaxActive(configConnection.getMaxPoolSize());
                    dataSource.setMinIdle(configConnection.getMinPoolSize());
                    dataSource.setInitialSize(configConnection.getInitialPoolSize());

                    dataSource.setPoolPreparedStatements(true);
                    dataSource.setMinEvictableIdleTimeMillis(300000L);
                    dataSource.setTimeBetweenEvictionRunsMillis(60000L);
                    dataSource.setValidationQuery("SELECT 'x' FROM DUAL");
                    dataSource.setTestOnBorrow(false);
                    dataSource.setTestOnReturn(false);
                    dataSource.setTestWhileIdle(true);
                    dataSource.setDefaultAutoCommit(configConnection.isAutocommit());
                    dataSource.setPoolPreparedStatements(false);
                    dataSource.setMaxWait(60000L);
                    dataSource.setFilters("wall,stat");
                    return dataSource;
               }
               default:
               {
                    DriverManagerDataSource dataSource = new DriverManagerDataSource();
                    dataSource.setUrl(configConnection.getUrl());
                    dataSource.setDriverClassName(POUtil.getDriverClass(configConnection.getDatabaseType()));
                    dataSource.setUsername(configConnection.getUsername());
                    dataSource.setPassword(configConnection.getPassword());
                    return dataSource;
               }
            }
        }
        catch (PropertyVetoException e)
        {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("获取数据源失败", e);
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("获取数据源失败", e);
        }
    }
}
