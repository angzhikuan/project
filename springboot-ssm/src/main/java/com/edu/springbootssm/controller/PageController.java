/*******************************************************************************
 * Package: com.edu.springbootssm.controller
 * Type:    PageController
 * Date:    2021/11/25 20:28
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/25 20:28
 */
@Controller
public class PageController {

    @RequestMapping("/test.html")
    public String test(){
        return "test";
    }

    @RequestMapping("/temp.html")
    public String temp(){

        return "temp";
    }


}
