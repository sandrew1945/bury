package com.sandrew.bury.bean;

public class LessEqualPack<T> extends Pack
{

    public LessEqualPack()
    {
    }

    public LessEqualPack(T value)
    {
        super(value);
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " <= ?";
    }
}
