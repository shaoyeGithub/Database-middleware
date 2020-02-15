package com.cbs.controller;

import com.cbs.entity.User;
import com.cbs.service.UserService;
import com.cbs.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : TerryZhang
 * @description：用户类
 * @date : 2020/2/14
 */
@RestController
@RequestMapping("/phoneNumber")
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("registerUser")
    public void registerUser(){
        logger.info("registerUser");
        User user = new User();
        userService.registerUser(user);
    }
}
