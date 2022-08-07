/*******************************************************************************
 * Package: com.edu
 * Type:    List
 * Date:    2021/12/1 18:40
 *
 * Copyright (c) 2021 HUANENG GUICHENG TRUST CORP.,LTD All Rights Reserved.
 *
 * You may not use this file except in compliance with the License.
 *******************************************************************************/
package com.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * TODO your comment
 *
 * @author Wangzhikuan
 * @date 2021/12/1 18:40
 */
public class List {

    @Test
    public void test() {

        ArrayList<Object> list = new ArrayList<>();
        list.add("A数据");
        list.add("asas" + "A数据");
        list.add("b数据");
        list.add("c数据");
        list.add("d数据");
        list.toArray();
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("2");
        list.add("2");
/*        LinkedHashSet<String> hashSet = new LinkedHashSet<>(list);
        for (String s : hashSet) {
            int index = list.indexOf(s);
            System.out.println(s + "-" + index);
        }
        System.out.println("============");
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list1.contains(list.get(i))) {
                list1.add(list.get(i));
            }
        }
        for (String s : list1) {
            int index = list1.indexOf(s);
            System.out.println(s + "--" + index);
        }

        System.out.println("============");
        for (String s : list) {
            int index = list.indexOf(s);
            System.out.println(s + "-" + index);
        }*/
        System.out.println("============");
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))){
                    list.remove(i);
                    i--;
                    break;
                }
            }
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}
