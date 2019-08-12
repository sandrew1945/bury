package com.sandrew.bury.lob.extractor;

import java.sql.*;

/**
 * Created by summer on 2019/7/12.
 */
public class DruidNativeJdbcExtractor extends NativeJdbcExtractorAdapter
{
    public DruidNativeJdbcExtractor() {
    }

    protected Connection doGetNativeConnection(Connection con) throws SQLException
    {
        return (Connection)con.unwrap(Connection.class);
    }

    public Statement getNativeStatement(Statement stmt) throws SQLException {
        return (Statement)stmt.unwrap(Statement.class);
    }

    public PreparedStatement getNativePreparedStatement(PreparedStatement ps) throws SQLException {
        return (PreparedStatement)ps.unwrap(PreparedStatement.class);
    }

    public CallableStatement getNativeCallableStatement(CallableStatement cs) throws SQLException {
        return (CallableStatement)cs.unwrap(CallableStatement.class);
    }

    public ResultSet getNativeResultSet(ResultSet rs) throws SQLException {
        return (ResultSet)rs.unwrap(ResultSet.class);
    }
}
