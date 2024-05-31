package com.jzj.demo.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActionType {
    STAT(1, "开始"),
    STOP(2, "暂停"),
    ACHEIEVE(3, "完成"),
    EXPIRE(4, "过期");

    private final int code;
    private final String msg;
}
