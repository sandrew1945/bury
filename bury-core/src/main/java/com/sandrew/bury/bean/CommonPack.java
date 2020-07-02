package com.sandrew.bury.bean;

public class CommonPack<T> extends Pack
{

    public CommonPack()
    {
    }

    public CommonPack(Object value)
    {
        super(value);
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " = ?";
    }
}
