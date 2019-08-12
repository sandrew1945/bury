package com.sandrew.bury.lob.extractor;

import java.sql.*;

/**
 * Created by summer on 2019/7/12.
 */
public class Jdbc4NativeJdbcExtractor extends NativeJdbcExtractorAdapter
{
    private Class<? extends Connection> connectionType = Connection.class;
    private Class<? extends Statement> statementType = Statement.class;
    private Class<? extends PreparedStatement> preparedStatementType = PreparedStatement.class;
    private Class<? extends CallableStatement> callableStatementType = CallableStatement.class;
    private Class<? extends ResultSet> resultSetType = ResultSet.class;

    public Jdbc4NativeJdbcExtractor() {
    }

    public void setConnectionType(Class<? extends Connection> connectionType) {
        this.connectionType = connectionType;
    }

    public void setStatementType(Class<? extends Statement> statementType) {
        this.statementType = statementType;
    }

    public void setPreparedStatementType(Class<? extends PreparedStatement> preparedStatementType) {
        this.preparedStatementType = preparedStatementType;
    }

    public void setCallableStatementType(Class<? extends CallableStatement> callableStatementType) {
        this.callableStatementType = callableStatementType;
    }

    public void setResultSetType(Class<? extends ResultSet> resultSetType) {
        this.resultSetType = resultSetType;
    }

    protected Connection doGetNativeConnection(Connection con) throws SQLException {
        return (Connection)con.unwrap(this.connectionType);
    }

    public Statement getNativeStatement(Statement stmt) throws SQLException {
        return (Statement)stmt.unwrap(this.statementType);
    }

    public PreparedStatement getNativePreparedStatement(PreparedStatement ps) throws SQLException {
        return (PreparedStatement)ps.unwrap(this.preparedStatementType);
    }

    public CallableStatement getNativeCallableStatement(CallableStatement cs) throws SQLException {
        return (CallableStatement)cs.unwrap(this.callableStatementType);
    }

    public ResultSet getNativeResultSet(ResultSet rs) throws SQLException {
        return (ResultSet)rs.unwrap(this.resultSetType);
    }
}