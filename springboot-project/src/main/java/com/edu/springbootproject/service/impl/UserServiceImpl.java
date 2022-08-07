/*******************************************************************************
 * Package: com.edu.springbootproject.service.impl
 * Type:    UserServiceImpl
 * Date:    2021/11/25 14:43
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootproject.service.impl;

import com.edu.springbootproject.dao.UserDao;
import com.edu.springbootproject.entity.User;
import com.edu.springbootproject.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/25 14:43
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User regisUser(User user) {
        int i = userDao.insertUser(user);
        if (i > 0) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> selectMap(int id) {
        Map<String, Object> map = userDao.selectMap(id);
        return map;
    }
}
