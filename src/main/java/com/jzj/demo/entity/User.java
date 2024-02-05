package com.jzj.demo.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;

}
