package com.sandrew.bury;

import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.configuration.Configuration;
import com.sandrew.bury.exception.POException;
import com.sandrew.bury.exception.UnsupportedMethodException;
import com.sandrew.bury.executor.Executor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer on 2019/5/30.
 */
public class MySqlSession extends DefaultSession
{
    public MySqlSession(Configuration configuration, Executor executor)
    {
        super(configuration, executor);
    }

    @Override
    public <T> PageResult<T> pageQuery(String sql, List<Object> params, DAOCallback<T> callback, int pageSize, int curPage) throws POException
    {
        PageResult<T> ps = new PageResult<T>();
        ps.setCurPage(curPage);
        ps.setPageSize(pageSize);
        ps.setTotalRecords(count(sql, params));
        // 根据SQL设置总记录数
        // 根据每页的记录条数及总记录数设置分页情况
        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        sb.append(" LIMIT ?, ?");
        if (null == params)
        {
            params = new ArrayList<Object>();
        }
        // 设置分页SQL的参数
        params.add(new Integer(pageSize * (curPage - 1)));
        params.add(new Integer(pageSize));
        List<T> records = select(sb.toString(), params, callback);
        ps.setRecords(records);
        // 获取总页数
        // 算法	总记录数 / 每页大小 + 如果 总记录数 % 每页大小 为 0 则 + 0 否则 + 1
        if (pageSize == 0)
        {
            throw new POException("pageSize can't be zero");
        }
        int pageCount = ps.getTotalRecords() / pageSize + (ps.getTotalRecords() % pageSize == 0 ? 0 : 1);
        ps.setTotalPages(pageCount);
        return ps;
    }

    @Override
    public int insertForLob(String sql, List<Object> params)
    {
        return executor.update(sql, params, null);
    }

    @Override
    public int updateForLob(String sql, List<Object> params) throws POException
    {
        return executor.update(sql, params, null);
    }

    @Override
    public <T> List<T> callProcedure(String procedureName, List<Object> ins, DAOCallback<T> callback) throws POException
    {
        throw new UnsupportedMethodException("MYSQL暂时不支持该方法");
    }

    @Override
    public Object getPK(String sequenceName) throws POException
    {
        throw new UnsupportedMethodException("MYSQL暂时不支持该方法");
    }
}
