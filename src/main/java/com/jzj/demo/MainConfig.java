package com.jzj.demo;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.jzj.demo.controller"})
public class MainConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

    }
}
