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
     * @Author summer
     * @Description 调用function
     * @Date 10:41 2021/11/8
     * @Param [functionName, ins, outType]
     * @return java.lang.Object
     **/
    Object callFunction(String sql, List<Object> ins, int outType);

    /**
     * @Author summer
     * @Description 调用procedure
     * @Date 14:08 2021/11/8
     * @Param [procedureName, ins, outs]
     * @return java.util.List<java.lang.Object>
     **/
    List<Object> callProcedure(String sql, List<Object> ins, List<Integer> outs);

    /**
     * @Author summer
     * @Description 调用procedure
     *              特殊的Procedure调用,只返回一个CURSOR
     * @Date 14:09 2021/11/8
     * @Param [procedureName, ins, callback]
     * @return java.util.List<T>
     **/
    <T> List<T> callProcedure(String sql, List<Object> ins, DAOCallback<T> callback);
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
