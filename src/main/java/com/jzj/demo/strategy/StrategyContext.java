package com.jzj.demo.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyContext {
    private static final Map<String, Strategy> registerMap = new HashMap<>();

    //注册策略
    public static void registerStrategy(String type, Strategy strategy){
        registerMap.putIfAbsent(type, strategy);
    }

    //获取策略
    public static Strategy getStrategy(String type){
        return registerMap.get(type);
    }
}
