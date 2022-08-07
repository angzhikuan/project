/*******************************************************************************
 * Package: com.edu.fmmall.vo
 * Type:    ResuleVo
 * Date:    2021/11/27 23:22
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.fmmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/27 23:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {

    // 响应给前端的状态码
    private int code;

    // 响应给前端的提示信息
    private String msg;

    // 响应给前端的数据
    private Object data;
}
