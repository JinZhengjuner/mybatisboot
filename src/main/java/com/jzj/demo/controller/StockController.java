package com.jzj.demo.controller;

import com.jzj.demo.tool.Desensitization;
import com.jzj.demo.tool.TypeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StockController {

    @GetMapping("/stock/test")
    @Desensitization
    public TypeResult test() throws InterruptedException {
        log.info("test调用了，,,,");
        return new TypeResult("15351253288", "510724199510280831", "694490246@qq.com");
    }

    @GetMapping("/stock/test1")
    @Desensitization
    public TypeResult test1() throws InterruptedException {
        log.info("test1调用了，,,,");
        return new TypeResult("15351253288", "510724199510280831", "694490246@qq.com");
    }
}
