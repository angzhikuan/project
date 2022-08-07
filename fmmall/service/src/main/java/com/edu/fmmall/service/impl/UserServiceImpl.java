/*******************************************************************************
 * Package: com.edu.fmmall.service.impl
 * Type:    UserServiceImpl
 * Date:    2021/11/27 23:15
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.fmmall.service.impl;

import com.edu.fmmall.dao.UserDao;
import com.edu.fmmall.entity.User;
import com.edu.fmmall.service.UserService;
import com.edu.fmmall.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/27 23:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public ResultVo checkLogin(String name, String pwd) {
        User user = userDao.queryUserByName(name);
        if (user == null) {
            //用户不存在
            return new ResultVo(1,"用户名不存在",null);
        } else {
            if (user.getUserPwd().equals(pwd)) {
                //验证成功
                return new ResultVo(2,"登录成功",user);
            }else {
                //密码错误
                return new ResultVo(3,"密码错误",null);
            }
        }
    }
}
