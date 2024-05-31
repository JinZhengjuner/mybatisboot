package com.jzj.demo.strategy;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Waimai extends AbstractStrategy implements Strategy{

    @PostConstruct
    public void init(){
        register();
    }

    @Override
    public void sendCoupon() {
        System.out.println("发放外卖优惠券");
    }

}
