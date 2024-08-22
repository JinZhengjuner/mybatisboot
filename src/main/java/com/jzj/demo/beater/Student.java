package com.jzj.demo.beater;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private long id;
    private String name;
    private long sno;
    private long age;

    public static void main(String[] args) {
        ArrayList<Student> students = Lists.newArrayList(new Student(1L, "张三", 20210001L, 18L),
                new Student(2L, "李四", 20210002L, 19L),
                new Student(3L, "王五", 20210003L, 20L));
        Predicate<Student> predicate = it -> it.getName().equals("张三");

        List<Student> list = get(it -> it.getName().equals("张三"), students);

    }

    public static List<Student> get(Predicate<Student> studentPredicate,List<Student> list){
        return list.stream().filter(studentPredicate).collect(Collectors.toList());
    }
}
