package com.sandrew.bury.bean;

public class EqualPack<T> extends Pack
{

    public EqualPack()
    {
    }

    public EqualPack(T value)
    {
        super(value);
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " = ?";
    }
}
