package com.baihy.core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ProjectName: multidatasource
 * @packageName: com.baihy.config
 * @Description:
 * @author: huayang.bai
 * @date: 2018-12-10 19:17
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}
