/*******************************************************************************
 * Package: com.edu.springbootproject.controller
 * Type:    UserController
 * Date:    2021/11/25 14:46
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootproject.controller;

import com.edu.springbootproject.entity.User;
import com.edu.springbootproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/25 14:46
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/regis")
//    @ResponseBody
    public String regis(User user){
        userService.regisUser(user);
        return "index";
    }

    @PostMapping("/test-map")
    public Map<String,Object> testMap(int id){
        return userService.selectMap(id);
    }
}
