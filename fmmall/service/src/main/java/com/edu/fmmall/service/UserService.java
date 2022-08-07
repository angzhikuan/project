/*******************************************************************************
 * Package: com.edu.fmmall.service
 * Type:    UserService
 * Date:    2021/11/27 23:14
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.fmmall.service;

import com.edu.fmmall.entity.User;
import com.edu.fmmall.vo.ResultVo;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/27 23:14
 */
public interface UserService {

    ResultVo checkLogin(String name, String pwd);
}
