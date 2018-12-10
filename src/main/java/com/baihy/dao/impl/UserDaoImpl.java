package com.baihy.dao.impl;

import com.baihy.core.annotation.DataSource;
import com.baihy.dao.UserDao;
import com.baihy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: multidatasource
 * @packageName: com.baihy.dao.impl
 * @Description:
 * @author: huayang.bai
 * @date: 2018-12-10 19:13
 */
@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    @DataSource("dataSource1")
    public int save(User param) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_user(id, username, password) values(?,?,?)");
        return jdbcTemplate.update(sql.toString(), param.getId(), param.getUsername(), param.getPassword());
    }

    @Override
    @DataSource("dataSource2")
    public int insert(User param) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_user(id, username, password) values(?,?,?)");
        return jdbcTemplate.update(sql.toString(), param.getId(), param.getUsername(), param.getPassword());

    }
}
