package com.jzj.demo.controller;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.jzj.demo.entity.People;
import com.jzj.demo.entity.User;
import com.jzj.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/testBoot")
@Slf4j
public class UserController {
    @Scheduled(fixedRate = 2 * 1000)
    public void test() throws InterruptedException {
        log.info("定时任务......");
        Thread.sleep(3000);
    }

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(5);

        for (int i = 0; i < 50; i++) {
            double acquire = limiter.acquire(10);
            log.info("success： " + acquire);
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static int num = 0;

    @RequestMapping(value = "/data")
    public List<People> get(){
       return Lists.newArrayList(new People(1, "张三"), new People(2, "李四"), new People(3, "王五"));
    }


    @RequestMapping(value = "getUser/{id}", method = RequestMethod.GET)
    public String GetUser(@PathVariable int id) {
        String s = stringRedisTemplate.opsForValue().get("1");
        if (Objects.nonNull(s)){

            System.out.println("redis缓存获取数据:num " + num++);
            return s;
        }
        s = userService.getUserInfo(id).toString();
        System.out.println("mysql获取数据");
        stringRedisTemplate.opsForValue().set("1", s, 60, TimeUnit.SECONDS);
        return s;
    }

    //通过用户id删除用户
    //    http://localhost:8080/testBoot/delete?id=1(此处1为要删除的id）
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = userService.deleteById(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
    //根据用户id更新用户信息
    //http://localhost:8080/testBoot/update?id=2&userName=波波&passWord=123456&realName=lalala
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        int result = userService.Update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
    //插入新用户
    //    http://localhost:8080/testBoot/insert?id=100&userName=波波&passWord=123456&realName=lalala
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public User insert() {
        Map<String, String> map  = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        User user1 = new User(1, "jzj", "金", "");
        return userService.save(user1);
    }
    //打印所有用户信息
    //    http://localhost:8080/testBoot/selectAll
    @RequestMapping("/selectAll")
    @ResponseBody
    public List<User> ListUser() {
        return userService.selectAll();
    }

}
