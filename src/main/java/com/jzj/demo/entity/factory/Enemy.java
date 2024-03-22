package com.jzj.demo.entity.factory;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Enemy {
    protected int x;
    protected int y;

    //抽象方法，在地图上绘制
    public abstract void show();
}
