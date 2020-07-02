package com.sandrew.bury.bean;

public class GreaterPack<T> extends Pack
{

    public GreaterPack()
    {
    }

    public GreaterPack(T value)
    {
        super(value);
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " > ?";
    }
}
