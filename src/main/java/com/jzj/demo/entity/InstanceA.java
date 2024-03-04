package com.jzj.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceA implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Autowired
    private InstanceB instanceB;

    public InstanceB getInstanceB() {
        return instanceB;
    }

    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }

    public InstanceA(InstanceB instanceB) {
        this.instanceB = instanceB;
    }


    public InstanceA() {
        System.out.println("InstanceA实例化");
    }


    public void say() {
        System.out.println("I'm A");
    }
}
