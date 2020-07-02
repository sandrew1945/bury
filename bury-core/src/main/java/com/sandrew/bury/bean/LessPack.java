package com.sandrew.bury.bean;

public class LessPack<T> extends Pack
{

    public LessPack()
    {
    }

    public LessPack(T value)
    {
        super(value);
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " < ?";
    }
}
