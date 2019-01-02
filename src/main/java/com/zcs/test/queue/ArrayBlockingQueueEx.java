package com.zcs.test.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueEx {
    /**
     * 第一个参数 是创建多大的队列长度，第二个参数是是不是使用公平锁，ture是公平锁，false不是
     * 虽然已经指定数组长度大小，20表示的意思是 在同一时间最大能存放20个元素，如果一直消费，则可以一直放入
     */
    private static ArrayBlockingQueue queue = new ArrayBlockingQueue(20,true);

    public static void main(String[] args){
        Thread a = new Thread(()->{
            for(int i = 0;i<30;i++){
                boolean addResult = queue.offer(i);
                System.out.println(addResult);
            }
        });
        a.start();
        new Thread(()->{
            while (true){
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
