package com.zcs.test.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingleThreadExecutor {
    //创建一个单线程的线程池，如果一个线程因为异常而死亡，则线程池会新增一个线程处理任务
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(()->{
            System.out.println("子线程开始处理");
            int i = 1/0;
        });
        while (true){
            Thread.sleep(2000);
            executorService.execute(a);
        }
    }
}
