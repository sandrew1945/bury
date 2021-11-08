package com.sandrew.bury;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.common.POMapping;
import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.executor.Executor;
import com.sandrew.bury.sql.DefaultSqlCreatorImpl;
import com.sandrew.bury.sql.SqlCreator;
import com.sandrew.bury.util.BatchParameter;
import com.sandrew.bury.util.POUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by summer on 2019/5/22.
 */
public abstract class DefaultSession implements Session
{
    final static Logger logger = LoggerFactory.getLogger(DefaultSession.class);

    protected Configuration configuration;

    protected Executor executor;

    public DefaultSession(Configuration configuration, Executor executor)
    {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public int insert(String sql, List<Object> params)
    {
        return executor.update(sql, params, null);
    }

    @Override
    public abstract int insertForLob(String sql, List<Object> params);

    @Override
    public int insert(PO po) throws POException
    {
        // 获取PO的POMapping
        POMapping mapping = new POMapping(po);
        // 获取SQL
        SqlCreator creator = new DefaultSqlCreatorImpl();
        String sql = creator.insertCreator(mapping, po);
        // 封装参数List
        List<Object> params = POUtil.encapParams(mapping, po);
        return executor.update(sql, params, po);
    }

    @Override
    public int[] insertForBatch(String sql, List<BatchParameter> parameters) throws POException
    {
        return executor.insertBatch(sql, parameters);
    }

    @Override
    public int[] insert(List<? extends PO> poList) throws POException
    {
        if (null == poList || poList.size() == 0)
        {
            throw new POException("poList is null");
        }
        // 获取PO的POMapping
        POMapping mapping = new POMapping(poList.get(0));
        // 获取SQL
        SqlCreator creator = new DefaultSqlCreatorImpl();
        String sql = creator.insertAllCreator(mapping, poList.get(0));
        List<BatchParameter> parameters = new ArrayList<>();
        BatchParameter parameter = null;
        for (PO po : poList)
        {
            parameter = new BatchParameter();
            List<Object> params = POUtil.encapAllParams(mapping, po);
            logger.debug("params --> " + params);
            for (Object param : params)
            {
                parameter.add(param);
            }
            parameters.add(parameter);
        }
        return insertForBatch(sql, parameters);
    }

    @Override
    public int delete(String sql, List<Object> params) throws POException
    {
        return executor.update(sql, params, null);
    }

    @Override
    public int delete(PO po) throws POException
    {
        // 获取PO的POMapping
        POMapping mapping = new POMapping(po);
        SqlCreator creator = new DefaultSqlCreatorImpl();
        String sql = creator.deleteCreator(mapping, po);
        // 封装参数List
        List<Object> params = POUtil.encapParams(mapping, po);
        return executor.update(sql, params, po);
    }

    @Override
    public int update(String sql, List<Object> params) throws POException
    {
        return executor.update(sql, params, null);
    }

    @Override
    public int update(PO cond, PO value) throws POException
    {
        // 获取PO的POMapping
        POMapping mapping = new POMapping(cond);
        // 获取SQL
        SqlCreator creator = new DefaultSqlCreatorImpl();
        String sql = creator.updateCreator(mapping, cond, value);
        // 封装参数List
        // 值参数
        LinkedList<Object> params = POUtil.encapParams(mapping, value, cond);
        // 条件参数
        return update(sql, params);
    }

    @Override
    public abstract int updateForLob(String sql, List<Object> params) throws POException;

    @Override
    public List<HashMap<String, Object>> select(String sql, List<Object> params) throws POException
    {
        try
        {
            return executor.query(sql, params);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("select error", e);
        }
    }

    @Override
    public List<HashMap<String, Object>> selectForOrder(PO po, String order, String... colNames) throws POException
    {
        // 获取PO的POMapping
        POMapping mapping = new POMapping(po);
        // 获取SQL
        SqlCreator creator = new DefaultSqlCreatorImpl();
        String sql = creator.selectCreatorForOrder(mapping, po, order, colNames);
        List<Object> params = POUtil.encapParams(mapping, po);
        return select(sql, params);
    }

    @Override
    public <T> List<T> select(String sql, List<Object> params, DAOCallback<T> callback) throws POException
    {
        try
        {
            return this.executor.query(sql, params, callback);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("select error", e);
        }
    }


    @Override
    public <T> List<T> select(PO po) throws POException
    {
        try
        {
            // 获取PO的POMapping
            POMapping mapping = new POMapping(po);
            // 获取SQL
            SqlCreator creator = new DefaultSqlCreatorImpl();
            String sql = creator.selectCreator(mapping, po);
            List<Object> params = POUtil.encapParams(mapping, po);
            Class clz = po.getClass();
            DAOCallback callback = new POCallBack(clz);
            return select(sql, params, callback);
        }
        catch (POException e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("select error", e);
        }
    }


    @Override
    public <T> List<T> selectForOrder(PO po, DAOCallback<T> callback, String order, String... colNames) throws POException
    {
        // 获取PO的POMapping
        POMapping mapping = new POMapping(po);
        // 获取SQL
        SqlCreator creator = new DefaultSqlCreatorImpl();
        String sql = creator.selectCreatorForOrder(mapping, po, order, colNames);
        List<Object> params = POUtil.encapParams(mapping, po);
        return select(sql, params, callback);
    }

    @Override
    public <T extends PO> T selectById(T t) throws POException
    {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            ColumnName columeName = field.getAnnotation(ColumnName.class);
            if (null != columeName && columeName.isPK() != true)
            {
//                POUtil.invokeSetMethodByField(t, field.getName(), field.getType(), null);
                Class<?> cls = t.getClass();
                String methodName = POUtil.getMethodOfSetByFieldName(field.getName());
                Method meth = null;
                try
                {
                    meth = cls.getMethod(methodName, field.getType());
                    Object[] arglist = new Object[] { null };
                    meth.invoke(t, arglist);
                }
                catch (Exception e)
                {
                    throw new POException("select error", e);
                }
            }
        }
        List<T> list = select(t);
        if (null != list && list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    abstract public <T> PageResult<T> pageQuery(String sql, List<Object> params, DAOCallback<T> callback, int pageSize, int curPage) throws POException;

    @Override
    public Object callFunction(String functionName, List<Object> ins, int outType)
    {
        try
        {
            // 拼装SQL
            SqlCreator creator = new DefaultSqlCreatorImpl();
            String sql = creator.getProdOrFuncSql(functionName, ins, null, false);
            logger.debug("SQL =====>" + sql);
            return this.executor.callFunction(functionName, ins, outType);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("call function error!");
        }
    }

    @Override
    public List<Object> callProcedure(String procedureName, List<Object> ins, List<Integer> outs)
    {
        try
        {
            // 拼装SQL
            SqlCreator creator = new DefaultSqlCreatorImpl();
            String sql = creator.getProdOrFuncSql(procedureName, ins, outs, true);
            logger.debug("SQL =====>" + sql);
            return this.executor.callProcedure(sql, ins, outs);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("call procedure error!");
        }
    }

//    @Override
//    public <T> List<T> callProcedure(String procedureName, List<Object> ins, DAOCallback<T> callback)
//    {
//        try
//        {
//            // 拼装SQL
//            SqlCreator creator = new DefaultSqlCreatorImpl();
//            List<Integer> outs = new ArrayList<>();
//            outs.add(POTypes.CURSOR);
//            String sql = creator.getProdOrFuncSql(procedureName, ins, outs, true);
//            logger.debug("SQL =====>" + sql);
//            return this.executor.callProcedure(sql, ins, callback);
//        }
//        catch (Exception e)
//        {
//            logger.error(e.getMessage(), e);
//            throw new POException("call procedure error!");
//        }
//    }

    /* (non-Javadoc)
     * @see com.autosys.po3.Session#getIntegerPK(java.lang.String)
     */
    public Integer getIntegerPK(String sequenceName) throws POException
    {
        return new Integer(getStringPK(sequenceName));
    }

    /* (non-Javadoc)
     * @see com.autosys.po3.Session#getLongPK(java.lang.String)
     */
    public Long getLongPK(String sequenceName) throws POException
    {
        return new Long(getStringPK(sequenceName));
    }

    /* (non-Javadoc)
     * @see com.autosys.po3.Session#getStringPK(java.lang.String)
     */
    public String getStringPK(String sequenceName) throws POException
    {
        return getPK(sequenceName).toString();
    }

    public abstract Object getPK(String sequenceName) throws POException;

    /**
     * Function    : 获取查询的记录数
     * LastUpdate  : 2010-6-10
     *
     * @param sql
     * @param params
     * @return
     * @throws POException
     */
    protected int count(String sql, List<Object> params) throws POException
    {
        int count = 0;
        sql = "SELECT COUNT(*) as COUNT FROM (" + sql + ")";
        try
        {
            List<HashMap<String, Object>> list = select(sql, params);
            if (null != list && list.size() > 0)
            {
                count = Integer.valueOf(list.get(0).get("COUNT").toString()).intValue();
            }
            return count;
        }
        catch (Exception e)
        {
            throw new POException("count(*) execute error!", e);
        }
    }

    @Override
    public void commit() throws POException
    {
        try
        {
            this.executor.commit();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("commit error", e);
        }
    }

    @Override
    public void rollback() throws POException
    {
        try
        {
            this.executor.rollback();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("rollback error", e);
        }
    }

    @Override
    public void close() throws POException
    {
        try
        {
            this.executor.close();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("close connection error", e);
        }
    }

    @Override
    public byte[] readBlob(String colName, String sql, List<Object> params) throws POException
    {
        return new byte[0];
    }


}
