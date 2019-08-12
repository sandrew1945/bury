package com.sandrew.bury.transaction;

import com.sandrew.bury.datasource.DataSourceWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by summer on 2019/5/15.
 */
public class ManagedTransaction implements Transaction
{
    final static Logger logger = LoggerFactory.getLogger(ManagedTransaction.class);

    private DataSourceWrapper dataSource;

    private Connection connection;

    public ManagedTransaction(DataSourceWrapper dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        if (null == this.connection || this.connection.isClosed())
        {

//            Connection conn = dataSource.getConnection();
            Connection conn = DataSourceUtils.getConnection(this.dataSource.getDataSource());
            this.connection = conn;
            logger.debug("Get connnection :" + conn);
        }
        return this.connection;
    }

    @Override
    public void commit() throws SQLException
    {

    }

    @Override
    public void rollback() throws SQLException
    {

    }

    @Override
    public void close() throws SQLException
    {
        DataSourceUtils.releaseConnection(this.connection, this.dataSource.getDataSource());
    }
}
