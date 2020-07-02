package com.sandrew.bury.bean;

public abstract class Pack<T>
{
    private T value;

    public Pack()
    {
    }

    public Pack(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public String toNullSql(String columnName)
    {
        return columnName + " is null";
    }

    public abstract String toSql(String columnName);
}
