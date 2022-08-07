/*******************************************************************************
 * Package: com.edu.fmmall.controller
 * Type:    UserController
 * Date:    2021/11/27 23:31
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.fmmall.controller;

import com.edu.fmmall.service.UserService;
import com.edu.fmmall.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/27 23:31
 */
@Controller
@ResponseBody
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public ResultVo login(String name, String pwd) {
        return userService.checkLogin(name, pwd);
    }
}
