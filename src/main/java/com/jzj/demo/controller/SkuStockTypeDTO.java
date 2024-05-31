package com.jzj.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkuStockTypeDTO {

    public SkuStockDTO availableStockDTO;


    public SkuStockDTO composeStockDTO;


    public SkuStockDTO selfStockDTO;

    public static void main(String[] args) {
        test();
    }

    private static void test() {

    }

    private static void test1() throws InterruptedException {
        Thread.sleep(1);
    }

}
