package com.jzj.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuStockDTO {
    public String skuId;

    public Double quantity;

    public Double lockedQuantity;

    public Double validQuantity;

    public Long changeTime;

    public static void main(String[] args) throws IOException {
        ///Users/jinzhengjun/Documents/spring/mybatisboot/a.txt
//        File file = new File("a.txt","../../b.txt");
//
//        if (!file.exists()){
//            file.createNewFile();
//            String absolutePath = file.getAbsolutePath();
//            System.out.println(absolutePath);
//        }
        while (true){

        }
    }
}
