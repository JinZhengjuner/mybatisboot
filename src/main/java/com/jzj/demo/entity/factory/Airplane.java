package com.jzj.demo.entity.factory;

public class Airplane extends Enemy {

    public Airplane(int x, int y) {
        super(x, y);//调用父类构造子初始化坐标
    }

    @Override
    public void show() {
        System.out.println("飞机出现坐标：" + x + "," + y);
        System.out.println("飞机向玩家发起攻击……");
    }
}
