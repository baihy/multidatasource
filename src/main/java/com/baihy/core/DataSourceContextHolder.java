package com.baihy.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huayang.bai
 * @Description:当前线程数据源
 * @date 2018/6/9 10:13
 */
public class DataSourceContextHolder {

    public static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        log.debug("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        log.debug("获取{}数据源", contextHolder.get());
        return contextHolder.get();
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
