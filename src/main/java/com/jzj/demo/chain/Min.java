package com.jzj.demo.chain;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Min {
    private Integer stock;


    public static void main(String[] args) {
        ArrayList<String> list1 = Lists.newArrayList("马一", "辛苦了");
        ArrayList<String> list2 = Lists.newArrayList("马但是", "多少啊了");
        ArrayList<String> list3 = Lists.newArrayList("马发多少", "发了");
        List<String> result = Stream.of(list1, list2, list3).flatMap(list ->
                list.stream().filter(it -> it.startsWith("马"))).collect(Collectors.toList());
    }
}
