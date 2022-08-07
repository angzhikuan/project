/*******************************************************************************
 * Package: com.edu.springbootproject.service
 * Type:    UserService
 * Date:    2021/11/25 14:42
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootproject.service;

import com.edu.springbootproject.entity.User;

import java.util.Map;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/25 14:42
 */
public interface UserService {

    User regisUser(User user);

    Map<String,Object> selectMap(int id);
}
