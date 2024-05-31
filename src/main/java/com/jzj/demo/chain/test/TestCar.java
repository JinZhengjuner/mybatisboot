package com.jzj.demo.chain.test;

public class TestCar extends CarAbstract{
    @Override
    protected void fly() {
        System.out.println("实现类 ---fly");
    }

//    @Override
//    protected void run() {
//        System.out.println("实现类---run");
//    }

    public static void main(String[] args) {
        TestCar testCar = new TestCar();
        testCar.run();
        testCar.fly();
    }
}
