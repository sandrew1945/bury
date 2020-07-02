package com.sandrew.bury.bean;

public abstract class AbstractIntervalPack<T> extends Pack
{

    private T max;

    public AbstractIntervalPack()
    {
    }

    public AbstractIntervalPack(T min, T max)
    {
        super(min);
        this.max = max;
    }

    public T getMax()
    {
        return this.max;
    }
}
