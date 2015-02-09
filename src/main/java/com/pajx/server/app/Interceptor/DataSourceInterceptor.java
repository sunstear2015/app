package com.pajx.server.app.Interceptor;

import com.pajx.server.app.utils.database.CustomerContextHolder;
import org.aspectj.lang.JoinPoint;

/**
 * Created by admin on 2015/2/3.
 */
public class DataSourceInterceptor {
    public void setDataSourceOne(JoinPoint jp) {
        CustomerContextHolder.clearCustomerType();
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE1);
        System.out.println(CustomerContextHolder.getCustomerType()+"----setDataSourceOne-----");
    }

    public void setDataSourceTwo(JoinPoint jp) {
        CustomerContextHolder.clearCustomerType();
        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_ORACLE2);
        System.out.println(CustomerContextHolder.getCustomerType()+"----setDataSourceTwo-----");
    }
}
