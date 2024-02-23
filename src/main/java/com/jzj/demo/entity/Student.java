package com.jzj.demo.entity;

import lombok.Builder;

import java.util.HashMap;

@Builder
public class Student {
    private int stuStatus = 1;


    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.put("a", "a");
        map.put("远程","")
    }
}
