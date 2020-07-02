package com.sandrew.bury.bean;

public class GreaterLessEqualPack<T> extends AbstractIntervalPack
{

    public GreaterLessEqualPack()
    {
    }

    public GreaterLessEqualPack(T min, T max)
    {
        super(min, max);
    }

    @Override
    public String toSql(String columnName)
    {
        return "( " + columnName + " >= ? AND " + columnName + " <= ? )";
    }
}
