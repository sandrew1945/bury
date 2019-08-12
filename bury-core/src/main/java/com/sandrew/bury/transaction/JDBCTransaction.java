package com.sandrew.bury.transaction;

import com.sandrew.bury.datasource.DataSourceWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by summer on 2019/5/14.
 */
public class JDBCTransaction implements Transaction
{
    final static Logger logger = LoggerFactory.getLogger(JDBCTransaction.class);

    private DataSourceWrapper dataSource;

    private Connection connection;

    public JDBCTransaction(DataSourceWrapper dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        if (null == this.connection || this.connection.isClosed())
        {
            Connection conn = dataSource.getConnection();
            this.connection = conn;
            logger.debug("Get connnection :" + conn);
        }
        return this.connection;
    }

    @Override
    public void commit() throws SQLException
    {
        this.connection.commit();
    }

    @Override
    public void rollback() throws SQLException
    {
        this.connection.rollback();
    }

    @Override
    public void close() throws SQLException
    {
        this.connection.close();
    }
}
