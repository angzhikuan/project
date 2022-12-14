package com.edu.springbootproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edu.springbootproject.dao")
public class SpringbootProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootProjectApplication.class, args);
    }

}
