package com.sandrew.bury.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Function    : 参数类，用于批量插入操作
 * @author     : SuMMeR
 * CreateDate  : 2013-4-25
 * @version    :
 */
public class BatchParameter
{
    private List<Object> params;

    private Iterator<Object> it;

    private Result result;

    public BatchParameter()
    {
        params = new ArrayList<Object>();
        result = new Result();
    }

    /**
     *
     * Function    : 增加一个参数
     * LastUpdate  : 2013-4-25
     * @param param
     */
    public void add(Object param)
    {
        params.add(param);
    }

    /**
     *
     * Function    : 判断是否还有参数
     * LastUpdate  : 2013-4-25
     * @return
     */
    public boolean hasNext()
    {
        if (null == it)
        {
            it = params.iterator();
        }
        boolean hasNext = it.hasNext();
        if (hasNext)
        {
            result.value = it.next();
            result.index++;
        }
        else
        {
            result.value = null;
        }
        return hasNext;
    }

    /**
     *
     * Function    : 获取参数序列
     * LastUpdate  : 2013-4-25
     * @return
     */
    public int getIndex()
    {
        return result.index;
    }

    /**
     *
     * Function    : 获取参数
     * LastUpdate  : 2013-4-25
     * @return
     */
    public Object getValue()
    {
        return result.value;
    }


    @Override
    public String toString()
    {
        return "index:" + result.index + " value:" + result.value;
    }


    class Result
    {
        private int index = 0;

        private Object value;
    }
}