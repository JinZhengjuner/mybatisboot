package com.jzj.demo.big.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

@Slf4j
public class JobMain {


    /**
     * 把整个流程串在一起
     */
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "world count");
        // 指定job所在的jar包
        job.setJarByClass(JobMain.class);

        //制定源文件的读取方式和源文件的路径
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("hdfs://182.254.221.168:9000/input/wordcount"));

        job.setMapperClass(WordCountMapper.class);//指定Mapper k2
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setReducerClass(WordCountReducer.class);//指定Reducer
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //指定输出方式，和输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("hdfs://182.254.221.168:9000/output/wordcount"));

        //将job提交到yarn集群
        boolean b = job.waitForCompletion(true);

        //退出
        System.exit(b?0:1);


    }
}
