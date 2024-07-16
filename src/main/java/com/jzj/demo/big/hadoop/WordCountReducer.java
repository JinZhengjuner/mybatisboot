package com.jzj.demo.big.hadoop;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 1.继承Reducer类
 * 2.重写reduce方法 将K2,[V2] ---> k3,v3
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {


    @Override
    protected void reduce(Text key2, Iterable<LongWritable> val2, Context context) throws IOException, InterruptedException {
        // k2,v2 是  hello [1,1,1]
        Long count = 0L;
        for (LongWritable longWritable : val2) {
            count+= longWritable.get();
        }
        context.write(key2, new LongWritable(count));
    }
}