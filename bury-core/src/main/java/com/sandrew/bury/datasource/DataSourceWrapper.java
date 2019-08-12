package com.sandrew.bury.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by summer on 2019/5/13.
 */
public class DataSourceWrapper
{
    final static Logger logger = LoggerFactory.getLogger(DataSourceWrapper.class);
    // 是否是默认连接
    private boolean isDefault;
    // 数据库类型
    private String databaseType;
    // 数据源名称
    private String dataSourceName;
    // 数据源
    private DataSource dataSource;
    // 数据连接
    private Connection connection;

    private boolean autocommit;

    public Connection getConnection() throws SQLException
    {
        if (null == this.dataSource)
        {
            throw new SQLException("datasource is null!");
        }
        this.connection = dataSource.getConnection();
        this.connection.setAutoCommit(autocommit);
        return this.connection;
    }

    public void setDefault()
    {
        this.isDefault = true;
    }

    public boolean isDefault()
    {
        return this.isDefault;
    }

    public String getDatabaseType()
    {
        return databaseType;
    }

    public void setDatabaseType(String databaseType)
    {
        this.databaseType = databaseType;
    }

    public String getDataSourceName()
    {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName)
    {
        this.dataSourceName = dataSourceName;
    }

    public DataSource getDataSource()
    {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public boolean isAutocommit()
    {
        return autocommit;
    }

    public void setAutocommit(boolean autocommit)
    {
        this.autocommit = autocommit;
    }
}
