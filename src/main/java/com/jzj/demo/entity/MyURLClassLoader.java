package com.jzj.demo.entity;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class MyURLClassLoader extends ClassLoader{
    private InstanceA url;

   private Integer age;




    public static void main(String[] args) {
        MyURLClassLoader url1 = new MyURLClassLoader(new InstanceA(),3);
        MyURLClassLoader url2 = new MyURLClassLoader(new InstanceA(),null);
        MyURLClassLoader url3 = new MyURLClassLoader(new InstanceA(),2);
        ArrayList<MyURLClassLoader> list = Lists.newArrayList(url1, url2, url3);

        list.stream().filter(x -> x.getAge()!=null).
                collect(Collectors.toMap(MyURLClassLoader::getUrl, MyURLClassLoader::getAge, (f, s) -> s));


    }
}
