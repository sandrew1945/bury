package com.sandrew.bury.bean;

public class NotEqualPack<T> extends Pack
{

    public NotEqualPack()
    {
    }

    public NotEqualPack(T value)
    {
        super(value);
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " <> ?";
    }
}
