package com.jzj.demo.tool;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class TimeResult {

//    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8") 每个都要写
    private Date createTime;

    private Date updateTime;

    private Date deleteTime;
}
