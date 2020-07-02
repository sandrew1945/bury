package com.sandrew.bury.bean;

public class GreaterLessPack<T> extends AbstractIntervalPack
{

    public GreaterLessPack()
    {
    }

    public GreaterLessPack(T min, T max)
    {
        super(min, max);
    }

    @Override
    public String toSql(String columnName)
    {
        return "( " + columnName + " > ? AND " + columnName + " < ? )";
    }
}
