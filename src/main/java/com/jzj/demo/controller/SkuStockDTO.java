package com.jzj.demo.controller;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        SkuStockDTO skuStockDTO = new SkuStockDTO("1", 100.0, 10.0, 90.0, 1631504800000L);
        SkuStockDTO skuStockDTO1 = new SkuStockDTO("21", 100.0, 10.0, 90.0, 1631504800000L);
        List<SkuStockDTO> list = Lists.newArrayList(skuStockDTO, skuStockDTO1);
        String s = list.stream().collect(Collectors.groupingBy(SkuStockDTO::getSkuId)).entrySet().stream().filter(x -> x.getValue().size() > 1).map(x -> x.getKey()).findAny().orElse(null);
        System.out.println(s);
    }
}
