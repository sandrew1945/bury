package com.sandrew.bury.executor;

import com.sandrew.bury.bean.PO;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.lob.LobHandler;
import com.sandrew.bury.lob.OracleLobHandler;
import com.sandrew.bury.lob.extractor.NativeJdbcExtractor;
import com.sandrew.bury.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

/**
 * Created by summer on 2019/5/29.
 */
public class OracleExecutor extends BaseExecutor
{
    final static Logger logger = LoggerFactory.getLogger(OracleExecutor.class);

    public OracleExecutor(String databaseType, Transaction transaction, NativeJdbcExtractor nativeJdbcExtractor)
    {
        super(databaseType, transaction, nativeJdbcExtractor);
    }


    @Override
    public int update(String sql, List<Object> params, PO po)
    {
        logger.debug("SQL =====>" + sql + " ; params:" + params);
        Connection conn = null;
        PreparedStatement ps = null;
        LobHandler handler = null;
        try
        {
            conn = transaction.getConnection();
            Connection conToUse = null;
            if (nativeJdbcExtractor != null)
            {
                conToUse = nativeJdbcExtractor.getNativeConnection(conn);
            }
            ps = conToUse.prepareStatement(sql);
            PreparedStatement psToUse = null;
            if (nativeJdbcExtractor != null)
            {
                psToUse = nativeJdbcExtractor.getNativePreparedStatement(ps);
            }
            if (null != params && params.size() > 0)
            {
                handler = new OracleLobHandler(conToUse);
                for (int i = 0; i < params.size(); i++)
                {
                    setParam(ps, i + 1, params.get(i), handler);
                }
            }
            return psToUse.executeUpdate();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new POException("update error!");
        }
        finally
        {
            try
            {
                if (null != handler)
                {
                    handler.releaseLobs();
                }
                closeResultSetAndStatment(null, ps);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
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
    private void setParam(PreparedStatement ps, int idx, Object obj, LobHandler handler) throws POException
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
                handler.setBlobAsBinaryStream(ps, idx, is, is.available());
                return;
            }
            if (obj instanceof Blob)
            {
                ps.setBlob(idx, (Blob) obj);
                return;
            }
            if (obj instanceof Clob)
            {
                ps.setClob(idx, (Clob) obj);
                return;
            }
            if (obj instanceof BigDecimal)
            {
                ps.setBigDecimal(idx, (BigDecimal) obj);
                return;
            }
            if (obj instanceof BigInteger)
            {
                ps.setBigDecimal(idx, new BigDecimal((BigInteger) obj));
                return;
            }
            if (obj instanceof Boolean)
            {
                ps.setInt(idx, (Boolean) obj ? 0 : 1);
                return;
            }
            if (obj instanceof Integer)
            {
                ps.setInt(idx, ((Integer) obj).intValue());
                return;
            }
            if (obj instanceof Long)
            {
                ps.setLong(idx, ((Long) obj).longValue());
                return;
            }
            if (obj instanceof String)
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
        catch (Exception e)
        {
            logger.error("ps set params error!", e);
            throw new POException("ps set params error!", e);
        }
    }

}
