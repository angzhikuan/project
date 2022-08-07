package com.edu.springbootssm;

import org.junit.jupiter.api.Test;
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

    @Test
    public void test1(){
        System.out.println("aaaa");
    }

    @Test
    public void test2(){
        System.out.println("true = angzhikuan ");
    }

}
