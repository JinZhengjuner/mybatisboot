package com.jzj.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class StockController {

    private static AtomicInteger num = new AtomicInteger(1);
    @GetMapping("/stock/test")
    public String test() throws InterruptedException {
        Thread.sleep(2000);
        log.info("调用了，num: {}", num.getAndIncrement());
        return "success";
    }
}
