/*******************************************************************************
 * Package: com.edu.springbootssm.entity
 * Type:    Book
 * Date:    2021/11/26 9:43
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu.springbootssm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/11/26 9:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private double bookPrice;
}
