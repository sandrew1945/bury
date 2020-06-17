package com.sandrew.bury.executor;

import com.sandrew.bury.bean.PO;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.util.BatchParameter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by summer on 2019/5/29.
 */
public interface Executor
{

    /**
     *  查询操作
     * @param sql
     * @param params
     * @return
     */
    List<HashMap<String, Object>> query(String sql, List<Object> params);

    /**
     *  查询操作
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> query(String sql, List<Object> params, DAOCallback<T> callback);

    /**
     *  增删改操作
     * @param sql
     * @param params
     * @return
     */
    int update(String sql, List<Object> params, PO po);

    /**
     *  批量新增
     * @param sql
     * @param params
     * @return
     */
    int[] insertBatch(String sql, List<BatchParameter> params);

    /**
     *  提交
     */
    void commit() throws SQLException;

    /**
     *  回滚
     */
    void rollback() throws SQLException;

    /**
     *  关闭
     * @throws SQLException
     */
    void close() throws SQLException;
}
