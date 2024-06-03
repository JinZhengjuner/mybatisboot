package com.jzj.demo.strategy;

public abstract class AbstractStrategy implements Strategy{
    public void register(){
        StrategyContext.registerStrategy(getClass().getSimpleName(), this);
        //1
        //2
        //3
        //4
    }
}
