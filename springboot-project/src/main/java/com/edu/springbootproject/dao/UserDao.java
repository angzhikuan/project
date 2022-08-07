/*******************************************************************************
 * Package: com.edu.springbootproject.dao
 * Type:    UserDao
 * Date:    2021/11/25 14:42
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootproject.dao;

import com.edu.springbootproject.entity.User;

import java.util.Map;
import java.util.Objects;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/25 14:42
 */
public interface UserDao {

    int insertUser(User user);

    Map<String, Object> selectMap(int id);
}
