package com.sandrew.bury.configuration;

import com.sandrew.bury.datasource.DataSourceWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by summer on 2019/5/13.
 */
public class Configuration
{
    // 数据源集合
    public Map<String, DataSourceWrapper> dataSources = new HashMap<String, DataSourceWrapper>();
    // 事务
    //public String transactionManager;

    public void addDataSource(String id, DataSourceWrapper dataSourceWrapper)
    {
        this.dataSources.put(id, dataSourceWrapper);
    }

    public DataSourceWrapper getDataSource(String id)
    {
        return this.dataSources.get(id);
    }
}
