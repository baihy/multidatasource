package com.baihy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baihy.core.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: multidatasource
 * @packageName: com.baihy.config
 * @Description:
 * @author: huayang.bai
 * @date: 2018-12-10 14:11
 */

@Component
@PropertySource({"classpath:db.properties"})
@ComponentScan(basePackages = {"com.baihy"})
@EnableAspectJAutoProxy // 默认情况下是不会开启切面
public class DatabaseConfig {


    @Autowired
    private Environment env;


    @Bean(name = "dataSource1")
    public DataSource dataSource1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName("dataSource1");
        dataSource.setDriverClassName(env.getProperty("datasource.abc.driver-class-name"));
        dataSource.setUrl(env.getProperty("datasource.abc.url"));
        dataSource.setUsername(env.getProperty("datasource.abc.username"));
        dataSource.setPassword(env.getProperty("datasource.abc.password"));
        return dataSource;
    }

    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName("cwDataSourc2");
        dataSource.setDriverClassName(env.getProperty("datasource.def.driver-class-name"));
        dataSource.setUrl(env.getProperty("datasource.def.url"));
        dataSource.setUsername(env.getProperty("datasource.def.username"));
        dataSource.setPassword(env.getProperty("datasource.def.password"));
        return dataSource;
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("dataSource1", dataSource1());
        dsMap.put("dataSource2", dataSource2());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }


    @Bean
    public PlatformTransactionManager txManager(DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource dynamicDataSource) {
        return new JdbcTemplate(dynamicDataSource);
    }
}
