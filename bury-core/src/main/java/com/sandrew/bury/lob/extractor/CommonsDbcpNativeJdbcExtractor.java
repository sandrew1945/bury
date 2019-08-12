package com.sandrew.bury.lob.extractor;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.*;

/**
 * Created by summer on 2019/7/12.
 */
public class CommonsDbcpNativeJdbcExtractor extends NativeJdbcExtractorAdapter
{
    private static final String GET_INNERMOST_DELEGATE_METHOD_NAME = "getInnermostDelegate";

    public CommonsDbcpNativeJdbcExtractor() {
    }

    private static Object getInnermostDelegate(Object obj) throws SQLException
    {
        if(obj == null) {
            return null;
        } else {
            try {
                Class ex = obj.getClass();

                do {
                    if(Modifier.isPublic(ex.getModifiers())) {
                        Method getInnermostDelegate = ex.getMethod("getInnermostDelegate", (Class[])null);
//                        Object delegate = ReflectionUtils.invokeJdbcMethod(getInnermostDelegate, obj);
                        Object delegate = getInnermostDelegate.invoke(obj, (Object[]) null);
                        return delegate != null?delegate:obj;
                    }

                    ex = ex.getSuperclass();
                } while(ex != null);

                return obj;
            } catch (NoSuchMethodException var4) {
                return obj;
            } catch (SecurityException var5) {
                throw new IllegalStateException("Commons DBCP getInnermostDelegate method is not accessible: " + var5);
            }
            catch (InvocationTargetException e)
            {
                throw new IllegalStateException("Commons DBCP getInnermostDelegate method is not accessible");
            }
            catch (IllegalAccessException e)
            {
                throw new IllegalStateException("Commons DBCP getInnermostDelegate method is not accessible");
            }
        }
    }

    protected Connection doGetNativeConnection(Connection con) throws SQLException {
        return (Connection)getInnermostDelegate(con);
    }

    public Statement getNativeStatement(Statement stmt) throws SQLException {
        return (Statement)getInnermostDelegate(stmt);
    }

    public PreparedStatement getNativePreparedStatement(PreparedStatement ps) throws SQLException {
        return (PreparedStatement)this.getNativeStatement(ps);
    }

    public CallableStatement getNativeCallableStatement(CallableStatement cs) throws SQLException {
        return (CallableStatement)this.getNativeStatement(cs);
    }

    public ResultSet getNativeResultSet(ResultSet rs) throws SQLException {
        return (ResultSet)getInnermostDelegate(rs);
    }
}