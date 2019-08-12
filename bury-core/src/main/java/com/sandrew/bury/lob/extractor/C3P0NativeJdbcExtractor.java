package com.sandrew.bury.lob.extractor;

import com.mchange.v2.c3p0.C3P0ProxyConnection;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by summer on 2019/7/12.
 */
public class C3P0NativeJdbcExtractor extends NativeJdbcExtractorAdapter
{
    private final Method getRawConnectionMethod;

    public static Connection getRawConnection(Connection con) {
        return con;
    }

    public C3P0NativeJdbcExtractor() {
        try {
            this.getRawConnectionMethod = this.getClass().getMethod("getRawConnection", new Class[]{Connection.class});
        } catch (NoSuchMethodException var2) {
            throw new IllegalStateException("Internal error in C3P0NativeJdbcExtractor: " + var2.getMessage());
        }
    }

    public boolean isNativeConnectionNecessaryForNativeStatements() {
        return true;
    }

    public boolean isNativeConnectionNecessaryForNativePreparedStatements() {
        return true;
    }

    public boolean isNativeConnectionNecessaryForNativeCallableStatements() {
        return true;
    }

    protected Connection doGetNativeConnection(Connection con) throws SQLException
    {
        if(con instanceof C3P0ProxyConnection) {
            C3P0ProxyConnection cpCon = (C3P0ProxyConnection)con;

            try {
                return (Connection)cpCon.rawConnectionOperation(this.getRawConnectionMethod, (Object)null, new Object[]{C3P0ProxyConnection.RAW_CONNECTION});
            } catch (SQLException var4) {
                throw var4;
            } catch (Exception var5) {
//                ReflectionUtils.handleReflectionException(var5);
                throw new IllegalStateException("Method not found: " + var5.getMessage());
            }
        }

        return con;
    }
}
