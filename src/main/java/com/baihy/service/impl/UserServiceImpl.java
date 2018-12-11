package com.baihy.service.impl;

import com.baihy.dao.UserDao;
import com.baihy.domain.User;
import com.baihy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: multidatasource
 * @packageName: com.baihy.service
 * @Description:
 * @author: huayang.bai
 * @date: 2018-12-11 09:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public int save(User param) {
        return userDao.save(param);
    }

    @Override
    public int insert(User param) {
        return userDao.insert(param);
    }
}
