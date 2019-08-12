package com.sandrew.bury.transaction;

import com.sandrew.bury.datasource.DataSourceWrapper;

/**
 * Created by summer on 2019/5/15.
 */
public class TransactionFactory
{
    public static Transaction newTransaction(DataSourceWrapper dataSourceWrapper)
    {
        return new JDBCTransaction(dataSourceWrapper);
//        if (BuryConstants.TRANSACTION_MANAGER_JDBC.equalsIgnoreCase(transactionManager))
//        {
//            return new JDBCTransaction(dataSourceWrapper);
//        }
//        else if (BuryConstants.TRANSACTION_MANAGER_MANAGED.equalsIgnoreCase(transactionManager))
//        {
//            return new ManagedTransaction(dataSourceWrapper);
//        }
//        else
//        {
//            return new JDBCTransaction(dataSourceWrapper);
//        }
    }

}