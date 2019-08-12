package com.sandrew.bury.spring.transation;

import com.sandrew.bury.datasource.DataSourceWrapper;
import com.sandrew.bury.transaction.Transaction;

/**
 * Created by summer on 2019/5/15.
 */
public class SpringTransactionFactory
{
    public static Transaction newTransaction(DataSourceWrapper dataSourceWrapper)
    {
        return new ManagedTransaction(dataSourceWrapper);
    }

}