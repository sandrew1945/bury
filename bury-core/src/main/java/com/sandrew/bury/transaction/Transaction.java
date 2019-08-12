package com.sandrew.bury.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by summer on 2019/5/13.
 */
public interface Transaction
{
    /**
     *  获取连接
     * @return
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     *  提交
     */
    void commit() throws SQLException;

    /**
     *  回滚
     */
    void rollback() throws SQLException;

    /**
     *  关闭连接
     * @throws SQLException
     */
    void close() throws SQLException;
}
