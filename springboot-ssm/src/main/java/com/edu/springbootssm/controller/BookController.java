/*******************************************************************************
 * Package: com.edu.springbootssm.controller
 * Type:    BookController
 * Date:    2021/11/26 9:42
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootssm.controller;

import com.edu.springbootssm.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/26 9:42
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping("/query")
    public String queryBook(int bookId, Model model) {
        Book book = new Book(bookId, "java", "小智",50);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,"java","智哥1",30));
        books.add(new Book(2,"css","智哥2",40));
        books.add(new Book(3,"c++","智哥3",30));
        books.add(new Book(4,"html","智哥4",50));
        //简单类型
        model.addAttribute("price",3.33);
        //字符串
        model.addAttribute("str","Long long go");
        model.addAttribute("color","orange");
        //对象
        model.addAttribute("book", book);
        //集合
        model.addAttribute("books",books);
        return "test";
    }
}
