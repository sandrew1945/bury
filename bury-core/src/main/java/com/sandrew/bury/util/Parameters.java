package com.sandrew.bury.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer on 2019/7/29.
 */
public class Parameters
{
    private List<Object> params;

    public Parameters()
    {
        if (null == this.params)
        {
            this.params = new ArrayList<Object>();
        }
    }

    public Parameters(Object... params)
    {
        if (null == this.params)
        {
            this.params = new ArrayList<Object>();
        }
        for (Object param : params)
        {
            addParam(param);
        }
    }

    public void addParam(Object param)
    {
        this.params.add(param);
    }

    public void addLikeParam(Object param)
    {
        this.params.add("%" + param + "%");
    }

    public List<Object> getParams()
    {
        return this.params;
    }

}
