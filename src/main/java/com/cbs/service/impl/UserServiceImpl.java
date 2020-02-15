package com.cbs.service.impl;

import com.cbs.dao.PhoneDao;
import com.cbs.dao.UserDao;
import com.cbs.entity.User;
import com.cbs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : TerryZhang
 * @description：用户类
 * @date : 2020/2/14
 */
@Service

public class UserServiceImpl implements UserService {
    static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Override
    public void registerUser(User user){
        userDao.insertUser(user.getUser_id(),user.getUser_name());
    }
}
