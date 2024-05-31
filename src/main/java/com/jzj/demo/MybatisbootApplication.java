package com.jzj.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.jzj.demo.mapper") //扫描的mapper
@EnableScheduling
public class MybatisbootApplication {



    public static void main(String[] args) {
        SpringApplication.run(MybatisbootApplication.class, args);
    }

    @Data
    @AllArgsConstructor
    public static class A {
        private String a;
    }
}
