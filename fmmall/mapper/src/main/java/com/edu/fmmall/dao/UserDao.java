/*******************************************************************************
 * Package: com.edu.fmmall.dao
 * Type:    UserDao
 * Date:    2021/11/27 22:40
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.fmmall.dao;

import com.edu.fmmall.entity.User;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/27 22:40
 */
public interface UserDao {

    User queryUserByName(String name);
}
