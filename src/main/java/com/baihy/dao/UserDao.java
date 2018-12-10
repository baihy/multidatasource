package com.baihy.dao;

import com.baihy.domain.User;

/**
 * @ProjectName: multidatasource
 * @packageName: com.baihy.dao
 * @Description:
 * @author: huayang.bai
 * @date: 2018-12-10 19:09
 */
public interface UserDao {

    int save(User param);

    int insert(User param);

}
