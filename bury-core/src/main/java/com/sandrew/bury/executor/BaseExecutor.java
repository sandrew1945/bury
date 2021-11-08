package com.sandrew.bury.executor;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.common.POTypes;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.lob.extractor.NativeJdbcExtractor;
import com.sandrew.bury.transaction.Transaction;
import com.sandrew.bury.util.BatchParameter;
import com.sandrew.bury.util.BuryConstants;
import com.sandrew.bury.util.POUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

/**
 * Created by summer on 2019/5/29.
 */
public class BaseExecutor implements Executor
{
    final static Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    protected String databaseType;

    protected Transaction transaction;

    protected NativeJdbcExtractor nativeJdbcExtractor;

    public BaseExecutor(String databaseType, Transaction transaction, NativeJdbcExtractor nativeJdbcExtractor)
    {
        this.databaseType = databaseType;
        this.transaction = transaction;
        this.nativeJdbcExtractor = nativeJdbcExtractor;
    }

    @Override
    public List<HashMap<String, Object>> query(String sql, List<Object> params)
    {
        logger.debug("SQL =====>" + sql + " ; params:" + params);
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<HashMap<String, Object>> results = null;
        Connection conn = null;
        try
        {
            conn = this.transaction.getConnection();
            ps = conn.prepareStatement(sql);
            // 如果设置参数,设置查询参数
            if (null != params && params.size() > 0)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    setParam(ps, i + 1, params.get(i));
                }
            }
            rs = ps.executeQuery();
            results = new ArrayList<HashMap<String, Object>>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCounts = rsmd.getColumnCount();
            while (rs.next())
            {
                HashMap<String, Object> result = new HashMap<String, Object>();
                for (int i = 1; i <= columnCounts; i++)
                {
                    // Fix by weibin 2011-12-26 support other column type
                    result.put(rsmd.getColumnName(i), getValueByType(rs, i, rsmd.getColumnType(i)));
                }
                results.add((HashMap<String, Object>) result);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("query error!");
        }
        finally
        {
            closeResultSetAndStatment(rs, ps);
            //closeConnection(conn);
        }
        return results;
    }

    @Override
    public <T> List<T> query(String sql, List<Object> params, DAOCallback<T> callback)
    {
        logger.debug("SQL =====>" + sql + " ; params:" + params);
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<T> list = new LinkedList<T>();
        Connection conn = null;
        try
        {
            conn = this.transaction.getConnection();
            logger.debug("connection : " + conn);
            ps = conn.prepareStatement(sql);
            // 如果设置参数,设置查询参数
            if (null != params && params.size() > 0)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    setParam(ps, i + 1, params.get(i));
                }
            }
            rs = ps.executeQuery();
            while (rs.next())
            {
                list.add(callback.wrapper(rs, list.size() + 1));
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("query error!");
        }
        finally
        {
            closeResultSetAndStatment(rs, ps);
            //closeConnection(conn);
        }
        return list;
    }

    @Override
    public int update(String sql, List<Object> params, PO po)
    {
        logger.debug("SQL =====>" + sql + " ; params:" + params);
        Connection conn = null;
        PreparedStatement ps = null;
        try
        {
            conn = transaction.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (null != params && params.size() > 0)
            {
                for (int i = 0; i < params.size(); i++)
                {
                    setParam(ps, i + 1, params.get(i));
                }
            }
            int count = ps.executeUpdate();
            // 如果是mysql或者mssql数据库,并且是insert操作,那么返回自增主键
            if (!isOracle(databaseType) && isInsert(sql) && null != po)
            {
                // 执行完insert操作，获取生成的键值并赋值到PO中
                ResultSet rs = ps.getGeneratedKeys();
                // 如果有自增键值
                if (rs.next())
                {
                    // 获取PO的全部字段，并将key值赋给自动增长列
                    Class<? extends PO> clz = po.getClass();
                    Field[] fields = clz.getDeclaredFields();
                    int columnIndex = 1;
                    for (int i = 0; i < fields.length; i++)
                    {
                        ColumnName columnName = fields[i].getAnnotation(ColumnName.class);
                        if (null == columnName)
                        {
                            continue;
                        }
                        // 如果列为自增并且该列还未赋值，则将自增值保存到PO中
                        if ((true == columnName.autoIncrement()) && (null == POUtil.invokeGetMethodByField(po, fields[i].getName())))
                        {
                            int incrementKey = rs.getInt(columnIndex);
                            POUtil.invokeSetMethodByField(po, fields[i].getName(), fields[i].getType(), incrementKey);
                            columnIndex++;
                        }
                    }
                }
            }
            return count;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("update error!");
        }
        finally
        {
            closeResultSetAndStatment(null, ps);
            //closeConnection(conn);
        }
    }

    @Override
    public int[] insertBatch(String sql, List<BatchParameter> params)
    {
        logger.debug("SQL =====>" + sql);
        PreparedStatement ps = null;
        try
        {
            Connection conn = transaction.getConnection();
            ps = conn.prepareStatement(sql);
            Iterator<BatchParameter> it = params.iterator();
            while (it.hasNext())
            {
                BatchParameter param = it.next();
                while (param.hasNext())
                {
                    setParam(ps, param.getIndex(), param.getValue());
                }
                ps.addBatch();
            }
            return ps.executeBatch();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new POException("update error!");
        }
        finally
        {
            try
            {
                if (null != ps)
                {
                    ps.clearBatch();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            closeResultSetAndStatment(null, ps);
        }
    }

    @Override
    public Object callFunction(String sql, List<Object> ins, int outType)
    {
        CallableStatement cast = null;
        try
        {
            Connection conn = transaction.getConnection();
            cast = conn.prepareCall(sql);
            // 注册输出参数类型
            cast.registerOutParameter(1, outType);
            // 注册输入参数
            setProdOrFuncParameters(cast, ins, null, false);
            cast.execute();
            // 执行Function后获取输出参数
            List<Integer> outs = new LinkedList<>();
            outs.add(outType);
            List<Object> retList = getProdOrFuncOutParams(cast, ins, outs, false);
            return retList.get(0);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("call function error!");
        }
        finally
        {
            closeResultSetAndStatment(null, cast);
        }
    }

    @Override
    public List<Object> callProcedure(String sql, List<Object> ins, List<Integer> outs)
    {
        CallableStatement cast = null;
        try
        {
            Connection conn = transaction.getConnection();
            cast = conn.prepareCall(sql);
            setProdOrFuncParameters(cast, ins, outs, true);
            cast.execute();
            return getProdOrFuncOutParams(cast, ins, outs, true);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("call procedure error!");
        }
        finally
        {
            closeResultSetAndStatment(null, cast);
        }
    }

    @Override
    public <T> List<T> callProcedure(String sql, List<Object> ins, DAOCallback<T> callback)
    {
        List<T> list = new ArrayList<>();
        try
        {
            // 输出参数为一个CURSOR
            List<Integer> outs = new ArrayList<>();
            outs.add(POTypes.CURSOR);
            List<Object> retList = callProcedure(sql, ins, outs);
            // 返回一个ResultSet的List
            ResultSet rs = (ResultSet) retList.get(0);
            while (rs.next())
            {
                list.add(callback.wrapper(rs, list.size() + 1));
            }
            return list;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("call procedure error!");
        }
    }

    @Override
    public void commit() throws SQLException
    {
        try
        {
            transaction.commit();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new SQLException("commit error", e);
        }
    }

    @Override
    public void rollback() throws SQLException
    {
        try
        {
            transaction.rollback();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new SQLException("rollback error", e);
        }
    }

    @Override
    public void close() throws SQLException
    {
        try
        {
            transaction.close();
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new SQLException("close connection error", e);
        }
    }

    /**
     *
     * Function    : 设置PreparedStatement的参数
     * LastUpdate  : 2010-6-10
     * @param ps
     * @param idx
     * @param obj
     * @throws POException
     */
    private void setParam(PreparedStatement ps, int idx, Object obj) throws POException
    {
        try
        {
            if (obj instanceof java.util.Date)
            {
                ps.setTimestamp(idx, new Timestamp(((java.util.Date) obj).getTime()));
                return;
            }
            if (obj instanceof InputStream)
            {
                InputStream is = (InputStream) obj;
                //handler.setBlobAsBinaryStream(ps, idx, is, is.available());
                ps.setBinaryStream(idx, is, is.available());
                return;
            }
            if (obj instanceof java.sql.Blob)
            {
                ps.setBlob(idx, (java.sql.Blob) obj);
                return;
            }
            if (obj instanceof java.sql.Clob)
            {
                ps.setClob(idx, (java.sql.Clob) obj);
                return;
            }
            if (obj instanceof java.math.BigDecimal)
            {
                ps.setBigDecimal(idx, (java.math.BigDecimal) obj);
                return;
            }
            if (obj instanceof java.math.BigInteger)
            {
                ps.setBigDecimal(idx, new BigDecimal((BigInteger) obj));
                return;
            }
            if (obj instanceof java.lang.Boolean)
            {
                ps.setInt(idx, (Boolean) obj ? 0 : 1);
                return;
            }
            if (obj instanceof java.lang.Integer)
            {
                ps.setInt(idx, ((Integer) obj).intValue());
                return;
            }
            if (obj instanceof java.lang.Long)
            {
                ps.setLong(idx, ((Long) obj).longValue());
                return;
            }
            if (obj instanceof java.lang.String)
            {
                ps.setString(idx, obj.toString());
                return;
            }
            ps.setObject(idx, obj);
        }
        catch (SQLException e)
        {
            logger.error("ps set params error!", e);
            throw new POException("ps set params error!", e);
        }
        catch (IOException e)
        {
            logger.error("ps set params error!", e);
            throw new POException("ps set params error!", e);
        }
    }

    /**
     *
     * Function    : 根据数据库字端类型,获取值
     * LastUpdate  : 2011-12-26
     * @param rs
     * @param idx
     * @param type
     * @return
     * @throws SQLException
     */
    private Object getValueByType(ResultSet rs, int idx, int type) throws SQLException
    {
        if (rs == null)
        {
            throw new POException("Get Value Error!");
        }

        switch (type)
        {
            case POTypes.INTEGER:
                return rs.getInt(idx);
            case POTypes.BIGINT:
                return rs.getInt(idx);
            case POTypes.ARRAY:
                break;
            case POTypes.BINARY:
                break;
            case POTypes.BIT:
                break;
            case POTypes.BLOB:
                return rs.getBlob(idx);
            case POTypes.BOOLEAN:
                break;
            case POTypes.CHAR:
                break;
            case POTypes.CLOB:
                return rs.getClob(idx);
            case POTypes.DATALINK:
                break;
            case POTypes.DATE:
                return rs.getTime(idx);
            case POTypes.DECIMAL:
                break;
            case POTypes.DISTINCT:
                break;
            case POTypes.DOUBLE:
                return rs.getDouble(idx);
            case POTypes.FLOAT:
                rs.getFloat(idx);
                break;
            case POTypes.JAVA_OBJECT:
                break;
            case POTypes.LONGVARBINARY:
                break;
            case POTypes.LONGVARCHAR:
                break;
            case POTypes.NULL:
                return rs.getString(idx);
            case POTypes.NUMERIC:
                return rs.getString(idx);
            case POTypes.OTHER:
                break;
            case POTypes.REAL:
                break;
            case POTypes.REF:
                break;
            case POTypes.SMALLINT:
                break;
            case POTypes.STRUCT:
                // cast.registerOutParameter(idx, POTypes.STRUCT);
                break;
            case POTypes.TIME:
                return rs.getTimestamp(idx);
            case POTypes.TIMESTAMP:
                return rs.getTimestamp(idx);
            case POTypes.TINYINT:
                break;
            case POTypes.VARBINARY:
                break;
            case POTypes.VARCHAR:
                return rs.getString(idx);
        }
        return null;
    }


    /**
     *
     * Function    : 设置Procedure或Function的输入,输出参数
     * LastUpdate  : 2010-6-25
     * @param cast
     * @param ins
     * @param outs
     * @throws SQLException
     */
    private void setProdOrFuncParameters(CallableStatement cast, List<Object> ins, List<Integer> outs, boolean isProcedure) throws POException
    {
        if (null == cast)
        {
            throw new POException("CallableStatement isn't null");
        }
        try
        {
            int paramsPos = 1;
            if (null != ins)
            {
                for (int i = 0; i < ins.size(); i++)
                {
                    if (isProcedure)
                    {
                        setParam(cast, paramsPos, ins.get(i));
                    }
                    else
                    {
                        setParam(cast, paramsPos + 1, ins.get(i));
                    }
                    paramsPos++;
                }
            }
            if (null != outs)
            {
                for (int i = 0; i < outs.size(); i++)
                {
                    if (isProcedure)
                    {
                        registerOutParameter(cast, paramsPos, outs.get(i).intValue(), isProcedure);
                    }
                    else
                    {
                        throw new POException("Function can't set output parameters");
                    }
                    paramsPos++;
                }
            }
        }
        catch (SQLException throwables)
        {
            logger.error("ps set params error!", throwables);
            throw new POException("ps set params error!", throwables);
        }
    }

    /**
     *
     * Function    : 获取Procedure或Function的输出
     * LastUpdate  : 2010-6-12
     * @param cast
     * @param ins
     * @param outs
     * @param isProcedure
     * @return
     * @throws SQLException
     */
    private List<Object> getProdOrFuncOutParams(CallableStatement cast, List<Object> ins, List<Integer> outs, boolean isProcedure) throws SQLException
    {
        if (outs != null)
        {
            List<Object> retList = new LinkedList<Object>();
            int begin = ins == null ? 0 : ins.size();
            for (int i = 0; i < outs.size(); i++)
            {
                int index = begin + i + 1;
                switch (outs.get(i))
                {
                    case POTypes.ARRAY:
                        break;
                    case POTypes.BIGINT:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getLong(1));
                        }
                        else
                        {
                            retList.add(i, cast.getLong(index));
                        }
                        break;
                    case POTypes.BINARY:
                        break;
                    case POTypes.BIT:
                        break;
                    case POTypes.BLOB:
                        break;
                    case POTypes.BOOLEAN:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getBoolean(1));
                        }
                        else
                        {
                            retList.add(i, cast.getBoolean(index));
                        }
                        break;
                    case POTypes.CHAR:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getString(1));
                        }
                        else
                        {
                            retList.add(i, cast.getString(index));
                        }
                        retList.add(i, cast.getString(index));
                        break;
                    case POTypes.CLOB:
                        break;
                    case POTypes.DATALINK:
                        break;
                    case POTypes.DATE:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getDate(1));
                        }
                        else
                        {
                            retList.add(i, cast.getDate(index));
                        }
                        break;
                    case POTypes.DECIMAL:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getBigDecimal(1));
                        }
                        else
                        {
                            retList.add(i, cast.getBigDecimal(index));
                        }
                        break;
                    case POTypes.DISTINCT:
                        break;
                    case POTypes.DOUBLE:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getDouble(1));
                        }
                        else
                        {
                            retList.add(i, cast.getDouble(index));
                        }
                        break;
                    case POTypes.FLOAT:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getFloat(1));
                        }
                        else
                        {
                            retList.add(i, cast.getFloat(index));
                        }
                        break;
                    case POTypes.INTEGER:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getInt(1));
                        }
                        else
                        {
                            retList.add(i, cast.getInt(index));
                        }
                        break;
                    case POTypes.JAVA_OBJECT:
                        break;
                    case POTypes.LONGVARBINARY:
                        break;
                    case POTypes.LONGVARCHAR:
                        break;
                    case POTypes.NULL:// nullֵ�Ĵ���
                        retList.add(i, null);
                        break;
                    case POTypes.NUMERIC:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getBigDecimal(1));
                        }
                        else
                        {
                            retList.add(i, cast.getBigDecimal(index));
                        }
                        break;
                    case POTypes.OTHER:
                        break;
                    case POTypes.REAL:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getFloat(1));
                        }
                        else
                        {
                            retList.add(i, cast.getFloat(index));
                        }
                        break;
                    case POTypes.REF:
                        break;
                    case POTypes.SMALLINT:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getInt(1));
                        }
                        else
                        {
                            retList.add(i, cast.getInt(index));
                        }
                        break;
                    case POTypes.STRUCT:
                        break;
                    case POTypes.TIME:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getTime(1));
                        }
                        else
                        {
                            retList.add(i, cast.getTime(index));
                        }
                        break;
                    case POTypes.TIMESTAMP:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getTimestamp(1));
                        }
                        else
                        {
                            retList.add(i, cast.getTimestamp(index));
                        }
                        break;
                    case POTypes.TINYINT:
                        break;
                    case POTypes.VARBINARY:
                        break;
                    case POTypes.VARCHAR:
                        if (!isProcedure)
                        {
                            retList.add(i, cast.getString(1));
                        }
                        else
                        {
                            retList.add(i, cast.getString(index));
                        }
                        break;
                    case POTypes.CURSOR:
                        if (!isProcedure)
                        {
                            throw new RuntimeException("Function can't return cursor");
                        }
                        else
                        {
                            // TODO OracleCallableStatement无法找到，待优化
                            // ResultSet rs = ((OracleCallableStatement) cast).getCursor(index);
                            // retList.add(i, rs);
                        }
                        break;
                }
            }
            return retList;
        }
        else
        {
            return null;
        }
    }

    private void registerOutParameter(CallableStatement cast, int idx, int type, boolean isProcedure) throws SQLException
    {
        if (cast == null)
        {
            throw new POException("Register Out Parameter Error!");
        }

        switch (type)
        {
            case POTypes.INTEGER:
                cast.registerOutParameter(idx, POTypes.INTEGER);
                break;
            case POTypes.BIGINT:
                cast.registerOutParameter(idx, POTypes.BIGINT);
                break;
            case POTypes.ARRAY:
                break;
            case POTypes.BINARY:
                break;
            case POTypes.BIT:
                break;
            case POTypes.BLOB:
                // cast.registerOutParameter(idx, POTypes.BLOB);
                break;
            case POTypes.BOOLEAN:
                cast.registerOutParameter(idx, POTypes.BOOLEAN);
                break;
            case POTypes.CHAR:
                cast.registerOutParameter(idx, POTypes.CHAR);
                break;
            case POTypes.CLOB:
                break;
            case POTypes.DATALINK:
                break;
            case POTypes.DATE:
                cast.registerOutParameter(idx, POTypes.DATE);
                break;
            case POTypes.DECIMAL:
                cast.registerOutParameter(idx, POTypes.DECIMAL);
                break;
            case POTypes.DISTINCT:
                break;
            case POTypes.DOUBLE:
                cast.registerOutParameter(idx, POTypes.DOUBLE);
                break;
            case POTypes.FLOAT:
                cast.registerOutParameter(idx, POTypes.FLOAT);
                break;
            case POTypes.JAVA_OBJECT:
                break;
            case POTypes.LONGVARBINARY:
                break;
            case POTypes.LONGVARCHAR:
                break;
            case POTypes.NULL:
                cast.registerOutParameter(idx, POTypes.NULL);
                break;
            case POTypes.NUMERIC:
                cast.registerOutParameter(idx, POTypes.NUMERIC);
                break;
            case POTypes.OTHER:
                break;
            case POTypes.REAL:
                cast.registerOutParameter(idx, POTypes.REAL);
                break;
            case POTypes.REF:
                cast.registerOutParameter(idx, POTypes.REF);
                break;
            case POTypes.SMALLINT:
                cast.registerOutParameter(idx, POTypes.SMALLINT);
                break;
            case POTypes.STRUCT:
                // cast.registerOutParameter(idx, POTypes.STRUCT);
                break;
            case POTypes.TIME:
                cast.registerOutParameter(idx, POTypes.TIME);
                break;
            case POTypes.TIMESTAMP:
                cast.registerOutParameter(idx, POTypes.TIMESTAMP);
                break;
            case POTypes.TINYINT:
                break;
            case POTypes.VARBINARY:
                break;
            case POTypes.VARCHAR:
                cast.registerOutParameter(idx, POTypes.VARCHAR);
                break;
            case POTypes.CURSOR:
                cast.registerOutParameter(idx, POTypes.CURSOR);
                break;
        }
    }

    /**
     *
     * Function    : 关闭ResultSet,Statement
     * LastUpdate  : 2010-5-21
     * @param rs
     * @param st
     * @throws POException
     */
    protected void closeResultSetAndStatment(ResultSet rs, Statement st) throws POException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
            if (st != null)
            {
                st.close();
            }
        }
        catch (SQLException e)
        {
            throw new POException(e.getMessage(), e);
        }
    }

    /**
     *  关闭数据库连接
     * @param connection
     * @throws POException
     */
    protected void closeConnection(Connection connection) throws POException
    {
        try
        {
            if (null != connection)
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage(), e);
            throw new POException(e.getMessage(), e);
        }
    }

    /**
     *  判断数据库类型是否为Oracle
     * @param databaseType
     * @return
     */
    private boolean isOracle(String databaseType)
    {
        if (BuryConstants.DATABASE_TPYE_ORACLE.equals(databaseType))
        {
            return true;
        }
        return false;
    }

    /**
     *  判断是否为Insert语句
     * @param sql
     * @return
     */
    private boolean isInsert(String sql)
    {
        if (null == sql || "".equals(sql))
        {
            return false;
        }
        if (sql.trim().toLowerCase().startsWith("insert"))
        {
            return true;
        }
        return false;
    }

}
