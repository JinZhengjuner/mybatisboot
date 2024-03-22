package com.jzj.demo.entity.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Adapter implements TriplePin{

    private DualPin dualPin;
    @Override
    public void electrify(int l, int n, int e) {
        dualPin.electrify(l,n);
    }

    public static void main(String[] args) {
        new Adapter(new TV()).electrify(1,2,3);

    }
}
