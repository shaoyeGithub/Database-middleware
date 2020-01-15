package com.cbs.dao;

import com.cbs.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testInsertUser(){
        userDao.insertUser(13, "zhang");
    
    }

}
