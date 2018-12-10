package com.baihy.core.aop;

import com.baihy.core.annotation.DataSource;
import com.baihy.core.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author huayang.bai
 * @Description:
 * @date 2018/6/9 10:19
 */
@Aspect
@Order(1)
@Component
public class DynamicDataSourceAspect {

    private final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


    @Around("execution(* com.baihy.dao..*(..))")
    public Object switchDS(ProceedingJoinPoint point) throws Throwable {
        Class<?> className = point.getTarget().getClass();
        // 得到访问的方法对象
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        Method method = className.getMethod(methodName, argClass);
        String dataSourceName = null;
        if (className.isAnnotationPresent(DataSource.class)) {
            DataSource ds = className.getAnnotation(DataSource.class);
            dataSourceName = ds.value();
        } else {
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                // 取出注解中的数据源名
                dataSourceName = annotation.value();
            }
        }
        LOGGER.info("类{}的{}方法的数据源是：{}", className, methodName, dataSourceName);
        // 切换数据源
        DataSourceContextHolder.setDB(dataSourceName);
        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.clearDB();
        }
    }


}
