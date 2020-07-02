package com.sandrew.bury.bean;

public class BetweenPack<T> extends AbstractIntervalPack
{

    public BetweenPack()
    {
    }

    public BetweenPack(T min, T max)
    {
        super(min, max);
    }

    @Override
    public String toSql(String columnName)
    {
        return "( " + columnName + " BETWEEN ? AND ? )";
    }
}
