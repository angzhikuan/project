/*******************************************************************************
 * Package: com.edu.fmmall.entity
 * Type:    User
 * Date:    2021/11/27 23:29
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.fmmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/27 23:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String userName;
    private String userPwd;
    private String userRealname;
    private String userImg;
}
