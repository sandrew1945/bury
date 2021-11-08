package com.sandrew.bury.util;

/**
 * Created by summer on 2019/5/14.
 */
public interface BuryConstants
{
    String DEFAULT_DATASOURCE_KEY = "default";

    // 数据库类型
    String DATABASE_TPYE_ORACLE = "oracle";
    String DATABASE_TPYE_MYSQL = "mysql";
    String DATABASE_TPYE_MSSQL = "mssql";

    // 事务管理器类型
    String TRANSACTION_MANAGER_JDBC = "jdbc";
    String TRANSACTION_MANAGER_MANAGED = "managed";

    // 数据源类型
    String DATASOURCE_DIRECT = "direct";
    String DATASOURCE_C3P0 = "c3p0";
    String DATASOURCE_DRUID = "druid";

    /**
     * 	PO
     */
    final String PO_SUFFIX = "PO";


    /**
     * 生成java类型常量
     */
    // SCALE为0
    final int JAVA_TYPE_SCALE_0 = 0;

    // PRECISION为0
    final int JAVA_TYPE_PRECISION_0 = 0;

    // Integer类型的最大长度
    final int JAVA_TYPE_INTEGER_MAX = 9;

    // Long类型的最小长度
    final int JAVA_TYPE_LONG_MIN = 9;

    // Long类型的最大长度
    final int JAVA_TYPE_LONG_MAX = 19;

    // Long类型的最大长度
    final int JAVA_TYPE_FLOAT_MAX = 8;

    // Long类型的最大小数位
    final int JAVA_TYPE_FLOAT_SCALE_MAX = 4;


    /**
     * Procedure及Function
     */
    // 参数前缀
    String PROD_FUNC_PARAMS_PREFIX = "(";

    // 参数后缀
    String PROD_FUNC_PARAMS_SUFFIX = ")";

    // Function前缀
    String FUNCTION_PREFIX = "{? = call ";

    // Procedure前缀
    String PROCEDURE_PREFIX = "{ call ";

    // Procedure及Function后缀
    String PROD_FUNC_SUFFIX = "}";
}
