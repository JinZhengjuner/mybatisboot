package com.jzj.demo.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftAndDeepCloneTest implements Cloneable{

    private String name;

    private Integer age;

    private InstanceA instanceA;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SoftAndDeepCloneTest clone = (SoftAndDeepCloneTest) super.clone();
        clone.instanceA = (InstanceA) instanceA.clone();
        return clone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        SoftAndDeepCloneTest one = new SoftAndDeepCloneTest("张三", 1, new InstanceA());
        System.out.println(one);
        SoftAndDeepCloneTest clone = (SoftAndDeepCloneTest) one.clone();
        System.out.println(clone);
        clone.setAge(22);
        clone.setName("名字变了");
        clone.setInstanceA(new InstanceA());
        System.out.println("----------");
        System.out.println(one);
        System.out.println(clone);


    }
}
