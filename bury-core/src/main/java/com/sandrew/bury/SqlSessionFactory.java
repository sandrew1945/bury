package com.sandrew.bury;

/**
 * Created by summer on 2019/5/13.
 */
public interface SqlSessionFactory
{
    /**
     *  打开默认数据源的Session
     * @return
     */
    Session openSession();

    /**
     *  打开指定数据源的Session
     * @param datasourceKey
     * @return
     */
    Session openSession(String datasourceKey);
}
