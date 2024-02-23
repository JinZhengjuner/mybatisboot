package com.jzj.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jzj.demo.mapper") //扫描的mapper
public class MybatisbootApplication {

    public static void main(String[] args) {
        System.out.println(1);
        SpringApplication.run(MybatisbootApplication.class, args);
    }

}
