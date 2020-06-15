package com.sandrew.bury.bean;

public class CommonPack<T> implements Pack
{
    T value;

    public T getValue()
    {
        return this.value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    @Override
    public String toSql(String columnName)
    {
        return columnName + " = ?";
    }
}
