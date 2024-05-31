package com.jzj.demo.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Hotel extends AbstractStrategy implements Strategy{


    @PostConstruct
    public void init(){
        register();
    }

    @Override
    public void sendCoupon() {
        System.out.println("发放酒店优惠券");
    }
}
