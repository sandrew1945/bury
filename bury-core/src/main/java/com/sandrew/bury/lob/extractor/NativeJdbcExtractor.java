package com.sandrew.bury.lob.extractor;

import java.sql.*;

/**
 * Created by summer on 2019/7/12.
 */
public interface NativeJdbcExtractor
{
    boolean isNativeConnectionNecessaryForNativeStatements();

    boolean isNativeConnectionNecessaryForNativePreparedStatements();

    boolean isNativeConnectionNecessaryForNativeCallableStatements();

    Connection getNativeConnection(Connection var1) throws SQLException;

    Connection getNativeConnectionFromStatement(Statement var1) throws SQLException;

    Statement getNativeStatement(Statement var1) throws SQLException;

    PreparedStatement getNativePreparedStatement(PreparedStatement var1) throws SQLException;

    CallableStatement getNativeCallableStatement(CallableStatement var1) throws SQLException;

    ResultSet getNativeResultSet(ResultSet var1) throws SQLException;
}
