package com.sandrew.bury.bean;

public interface Pack<T>
{
    String toSql(String columnName);
}
