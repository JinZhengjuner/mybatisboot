package com.jzj.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class MybatisbootApplicationTests {

    private static ExecutorService executorService;

    @BeforeAll
    static void poll() {
        executorService =  Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), x -> {
            Thread thread = new Thread();
            System.out.println(x);
            thread.setName("my poll .....");
            return thread;
        });
    }

    String getName(String name){
        System.out.println("======" + Thread.currentThread().getName());
        return name;
    }
    @Test
    void demo() throws ExecutionException, InterruptedException {
//        //创建不带返回值的异步任务
//        CompletableFuture.runAsync(() -> this.getName("张三"), executorService);
//
//        //创建带返回值的异步任务
//        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService);
//        String name =cf1.get();//需要手动抛异常
//        String join = cf1.join();//方法抛出的是uncheck异常（RuntimeException），不会强制开发者抛出
//
//        //第一个任务执行后，执行新任务，可拿到第一个任务的返回结果，  第二个无法返回
        CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService).thenAccept(x -> System.out.println(x + "------" + Thread.currentThread().getName()));
        Thread.sleep(2);
//        CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService).thenAcceptAsync(a -> System.out.println(a + "qqq"));
//
//        //第一个任务执行后，执行新任务，可拿到第一个任务的返回结果，  第二个可以返回
//        CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService).thenApply(x -> {
//            System.out.println(x);
//            return x;
//        });
//        CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService).thenApplyAsync(x -> {
//            System.out.println(x);
//            return x;
//        });
//
//        //第一个任务执行后，执行新任务，可拿到第一个任务的返回结果，  第二个可以返回
//        //thenCompose来连接两个CompletableFuture，
//        // thenapply转换的是反省中的类型，是同一个CompletableFuture
//        CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService)
//                .thenCompose(res -> CompletableFuture.supplyAsync(() -> {
//            System.out.println("qqq");
//            return res;
//        }));
//
//
//        //todo 当不传线程池的情况下，会用ForkJoinPool中公共线程池CommonPool（线程池大小是cpu线程数-1）
//        //异步任务执行成功后，自动回调（主线程）
//        CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService).whenComplete((result, ex) -> {
//            System.out.println("所有任务执行完成，返回结果为：" + result);
//        });
//
//        CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService).whenCompleteAsync((result, ex) -> {
//            System.out.println("所有任务执行完成，返回结果为：" + result);
//        });
//
//        //todo 多个线程同时执行 ---1
//        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService);
//        CompletableFuture<String> cf4 = CompletableFuture.supplyAsync(() -> this.getName("张三"), executorService);
//        CompletableFuture.allOf(cf3, cf4).join();//同时执行,每个线程执行完才往下面走
//        String val1 = cf3.get();
//        String val2 = cf4.get();
//
//        // todo CompletableFuture数量不知道 ---2
//        List<String> list = Lists.newArrayList("张三", "李四", "王五", "赵六");
//        List<CompletableFuture<String>> completableFutureList = list.stream().map(it -> CompletableFuture.supplyAsync(() -> this.getName(it), executorService)).collect(Collectors.toList());
//        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])).join();
//        List<String> stringList = completableFutureList.stream().map(this::tryGet).filter(Objects::nonNull).collect(Collectors.toList());

        //todo ---3
    }

    private String tryGet(CompletableFuture<String> stringCompletableFuture) {
        try {
            return stringCompletableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
