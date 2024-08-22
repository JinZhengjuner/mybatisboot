package com.jzj.demo.tool;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeEnum {
    PHONE(1, "手机号"),
    MAIL(2, "邮箱"),
    CART(3, "身份证");

    private final int code;

    private final String type;
}
