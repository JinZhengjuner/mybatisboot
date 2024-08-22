package com.jzj.demo.tool;

import com.google.common.collect.Lists;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class StreamUtil {
    /**
     * Description：该方法用途是增强 Stream.forEach(),遍历的同时获取index
     */
    public static <T> Consumer<T> index(BiConsumer<Integer, T> biConsumer){
        class obj{
            int i;
        }
        obj obj = new obj();
        return t -> biConsumer.accept(obj.i++, t);
    }


    public static void main(String[] args) {
        Lists.newArrayList(1,2,3,4,5).forEach(StreamUtil.index((index, it) ->
                System.out.println("index:"+index+"  it:"+it)
        ));
    }

}
