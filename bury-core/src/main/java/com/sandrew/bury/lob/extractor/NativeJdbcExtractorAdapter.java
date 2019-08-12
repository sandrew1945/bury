package com.sandrew.bury.lob.extractor;

import java.sql.*;

/**
 * Created by summer on 2019/7/12.
 */
public abstract class NativeJdbcExtractorAdapter implements NativeJdbcExtractor
{
    public NativeJdbcExtractorAdapter() {
    }

    public boolean isNativeConnectionNecessaryForNativeStatements() {
        return false;
    }

    public boolean isNativeConnectionNecessaryForNativePreparedStatements() {
        return false;
    }

    public boolean isNativeConnectionNecessaryForNativeCallableStatements() {
        return false;
    }

    public Connection getNativeConnection(Connection con) throws SQLException
    {
        return this.doGetNativeConnection(con);
    }

    protected Connection doGetNativeConnection(Connection con) throws SQLException {
        return con;
    }

    public Connection getNativeConnectionFromStatement(Statement stmt) throws SQLException {
        return stmt == null?null:this.getNativeConnection(stmt.getConnection());
    }

    public Statement getNativeStatement(Statement stmt) throws SQLException {
        return stmt;
    }

    public PreparedStatement getNativePreparedStatement(PreparedStatement ps) throws SQLException {
        return ps;
    }

    public CallableStatement getNativeCallableStatement(CallableStatement cs) throws SQLException {
        return cs;
    }

    public ResultSet getNativeResultSet(ResultSet rs) throws SQLException {
        return rs;
    }
}
