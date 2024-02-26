package com.jzj.demo.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;

    public static void main(String[] args) {
        User build = User.builder().build();
    }

}
