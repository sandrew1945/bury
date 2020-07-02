package com.sandrew.bury.bean;

public class GreaterEqualPack<T> extends Pack
{

    public GreaterEqualPack()
    {
    }

    public GreaterEqualPack(T value)
    {
        super(value);
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " >= ?";
    }
}
