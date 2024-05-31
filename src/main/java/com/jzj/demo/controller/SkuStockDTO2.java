package com.jzj.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuStockDTO2 {

    public Long changeTime;

    public static void main(String[] args) {
        File file = new File("s.txt");
        System.out.println(file.exists());

    }
}
