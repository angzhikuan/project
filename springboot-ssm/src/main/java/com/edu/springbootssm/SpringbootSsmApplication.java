package com.edu.springbootssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//支持数组配置{“”,""}
@MapperScan("com.edu.springbootssm.dao")
public class SpringbootSsmApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootSsmApplication.class, args);
    }

}
