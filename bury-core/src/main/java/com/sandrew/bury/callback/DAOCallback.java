package com.sandrew.bury.callback;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAOCallback<T>
{
	public T wrapper(ResultSet rs, int index) throws SQLException;
}
