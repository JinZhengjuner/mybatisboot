package com.jzj.demo;

import com.jzj.demo.strategy.StrategyContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisbootApplicationTests {

    @Test
    void contextLoads() {
        StrategyContext.getStrategy("Waimai").sendCoupon();
    }

}
