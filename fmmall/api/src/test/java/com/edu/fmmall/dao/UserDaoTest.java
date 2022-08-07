package com.edu.fmmall.dao;


import com.edu.fmmall.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/*******************************************************************************
 * Package: com.edu.fmmall.dao
 * Type:    UserDaoTest
 * Date:    2021/11/27 22:47
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Resource
    private UserDao userDao;
    @Test
    public void queryUserByName() {
        User user = userDao.queryUserByName("lisi");

        System.out.println("user = " + user);
    }
}