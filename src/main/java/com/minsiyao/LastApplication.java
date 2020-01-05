package com.minsiyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.minsiyao.dao")
public class LastApplication {

    public static void main(String[] args) {
        SpringApplication.run(LastApplication.class, args);
        System.out.println("qwer");
        System.out.println("qwer");
        System.out.println("qwer");
        System.out.println("qwer");

    }

}
