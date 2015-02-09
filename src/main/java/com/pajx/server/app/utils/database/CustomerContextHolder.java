package com.pajx.server.app.utils.database;

/**
 * 多数据源
 * Created by admin on 2015/2/2.
 */
public class CustomerContextHolder {
    public final static String DATA_SOURCE_ORACLE1 = "oracleDataSource1";
    public final static String DATA_SOURCE_ORACLE2 = "oracleDataSource2";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }
    public static String getCustomerType() {
        return contextHolder.get();
    }
    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
