package com.jzj.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceB {

    @Autowired
    private InstanceA instanceA;


    public InstanceA getInstanceA() {
        //远程修改BBBBB
        return instanceA;
    }


    public void setInstanceA(InstanceA instanceA) {
        this.instanceA = instanceA;
    }

    public InstanceB(InstanceA instanceA) {
        this.instanceA = instanceA;
    }

    public InstanceB() {
        System.out.println("InstanceB实例化");
    }
}
