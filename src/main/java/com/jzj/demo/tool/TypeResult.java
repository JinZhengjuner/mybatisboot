package com.jzj.demo.tool;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeResult {

    @DesensitizationType(type = TypeEnum.PHONE)
    private String phoneNum;

    @DesensitizationType(type = TypeEnum.CART)
    private String card;

    @DesensitizationType(type = TypeEnum.MAIL)
    private String email;
}
