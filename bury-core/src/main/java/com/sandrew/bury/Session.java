package com.sandrew.bury;

import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.util.BatchParameter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by summer on 2019/5/13.
 */
public interface Session
{
    /**
     *
     * Function    : 通过SQL的insert方法
     * @param sql		SQL
     * @param params	参数集合
     * @return			插入记录数
     */
    int insert(String sql, List<Object> params);

    /**
     *
     * Function    : 通过SQL的insert方法(LOB版本)
     * @param sql
     * @param params
     * @return
     */
    int insertForLob(String sql, List<Object> params);

    /**
     *
     * Function    : 通过PO的insert方法
     * @param po		PO对象
     * @return			插入记录数
     * @throws POException
     */
    int insert(PO po) throws POException;

    /**
     * 批量插入
     * @param sql
     * @param parameters
     * @return
     * @throws POException
     */
    int[] insertForBatch(String sql, List<BatchParameter> parameters) throws POException;

    /**
     *   批量插入
     * @param poList
     * @return
     * @throws POException
     */
    int[] insert(List<? extends PO> poList) throws POException;

    /**
     *
     * Function    : 通过SQL的delete方法
     * @param sql		SQL
     * @param params	参数集合
     * @return			删除记录数
     * @throws POException
     */
    int delete(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过PO的delete方法
     * @param po		PO对象
     * @return			删除记录数
     * @throws POException
     */
    int delete(PO po) throws POException;

    /**
     *
     * Function    : 通过SQL的update方法
     * @param sql		SQL
     * @param params	参数集合
     * @return			更新记录数
     * @throws POException
     */
    int update(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过PO的update操作
     * @param cond		作为where条件的PO对象
     * @param value		作为更新值的PO对象
     * @return			更新记录数
     * @throws POException
     */
    int update(PO cond, PO value) throws POException;

    /**
     *
     * Function    : 通过SQL的update方法(LOB版本)
     * @param sql
     * @param params
     * @return
     * @throws POException
     */
    int updateForLob(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过SQL的select操作
     * @param sql		SQL
     * @param params	参数集合
     * @return			返回一个装载着Map的集合，Map的Key为表列名，Value为此列的值
     * @throws POException
     */
    List<HashMap<String, Object>> select(String sql, List<Object> params) throws POException;

    /**
     *
     * Function    : 通过PO的排序select操作
     * @param po		PO对象
     * @param order		排序方式 ASC|DESC
     * @param colNames	排序字段名，可多个
     * @return
     * @throws POException
     */
    List<HashMap<String, Object>> selectForOrder(PO po, String order, String... colNames) throws POException;

    /**
     *
     * Function    : 通过SQL的select操作
     * @param <T>
     * @param sql		SQL
     * @param params	参数集合
     * @param callback	回调函数，将结果集封装到PO或者自定义Bean里
     * @return			装载着PO或自定义Bean的集合
     * @throws POException
     */
    <T> List<T> select(String sql, List<Object> params, DAOCallback<T> callback) throws POException;

    /**
     *
     * Function    : 通过PO的select操作
     * @param <T>
     * @param po		PO对象
     * @return			装载着PO或自定义Bean的集合
     * @throws POException
     */
    <T> List<T> select(PO po) throws POException;

    /**
     *
     * Function    : 通过PO的排序select操作
     * @param <T>
     * @param po		PO对象
     * @param callback	回调函数
     * @param order		排序方式 ASC|DESC
     * @param colNames	排序字段，可多个
     * @return
     * @throws POException
     */
    <T> List<T> selectForOrder(PO po, DAOCallback<T> callback, String order, String... colNames) throws POException;


    /**
     *
     * Function    : 通过主键查询
     * @param <T>
     * @return
     * @throws POException
     */
    <T> T selectById(T t) throws POException;

    /**
     *
     * Function    : 分页查询操作
     * @param <T>
     * @param sql		SQL
     * @param params	参数集合
     * @param callback	回调函数
     * @param pageSize	页面显示记录数
     * @param curPage	当前页
     * @return			装载着PageResult对象的集合
     * @throws POException
     */
    <T> PageResult<T> pageQuery(String sql, List<Object> params, DAOCallback<T> callback, int pageSize, int curPage) throws POException;

    /**
     *
     * Function    : 调用Function
     * @param functionName	Function名称
     * @param ins			输入参数集合
     * @param outType		输出类型
     * @return
     */
    Object callFunction(String functionName, List<Object> ins, int outType);

    /**
     *
     * Function    : 调用Procedure
     * @param procedureName	Procedure名称
     * @param ins			输入参数集合
     * @param outs			输出参数集合
     * @return				输出结果集合
     */
    List<Object> callProcedure(String procedureName, List<Object> ins, List<Integer> outs);

    /**
     *
     * Function    : 特殊的Procedure调用,只返回一个CURSOR
     * @param <T>
     * @param procedureName	Procedure名称
     * @param ins			输入参数集合
     * @param callback		回调函数
     * @return				装载着PO或自定义bean的集合
     */
    <T> List<T> callProcedure(String procedureName, List<Object> ins, DAOCallback<T> callback);

    /**
     *
     * Function    : 获取Long类型序列ID
     * @param sequenceName	sequence名称
     * @return
     */
    Long getLongPK(String sequenceName);

    /**
     *
     * Function    : 获取Integer类型序列ID
     * @param sequenceName	sequence名称
     * @return
     */
    Integer getIntegerPK(String sequenceName);

    /**
     *
     * Function    : 获取String类型序列ID
     * @param sequenceName	sequence名称
     * @return
     */
    String getStringPK(String sequenceName);

    /**
     *
     * Function    : 提交
     */
    void commit() throws POException;

    /**
     *
     * Function    : 回滚
     */
    void rollback() throws POException;

    /**
     *  关闭Session
     * @throws POException
     */
    void close() throws POException;

    /**
     *
     * Function    : 读取BLOB字段
     * @param colName
     * @param sql
     * @param params
     * @return
     */
    byte[] readBlob(String colName, String sql, List<Object> params) throws POException;
}
