/*******************************************************************************
 * Package: com.edu.springbootproject.controller
 * Type:    PageController
 * Date:    2021/11/25 17:44
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/25 17:44
 */
@Controller
public class PageController {

    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }
}
