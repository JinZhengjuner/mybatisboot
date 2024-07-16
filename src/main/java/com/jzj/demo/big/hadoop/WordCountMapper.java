package com.jzj.demo.big.hadoop;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    //1.继承Mapper类,四个泛型  k1 v1 k2 k2  hadoop有自己的数据类型



    //2.重写map方法，将kv，v2 ---> k2 v2

    /**
     *
     k1  v1                   k2     v2
     0  hello,hadoop   -->   hello   1
                             hadoop   1
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split(",");
        //2.v2是固定值1
        //3.把k2，v2写入context中，传到下一个环节
        for (String str : split) {
            context.write(new Text(str), new LongWritable(1));
        }
    }
}
