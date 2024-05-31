package com.jzj.demo.entity;

import com.jzj.demo.controller.XiuShi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceA extends XiuShi implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //远程修改AAAAA
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
