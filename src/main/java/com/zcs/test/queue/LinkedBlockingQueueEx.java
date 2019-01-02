package com.zcs.test.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueEx {
    // 有界队列Integer的最大长度  先进先出队列  进队列和出队列都是独占锁进行处理
    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue();
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            for(int i =0;i<Integer.MAX_VALUE;i++){
                queue.offer(i);
            }
        }).start();
        while (true){
            Integer result = queue.take();
            if(result!=null){
                System.out.println(result);
            }
            break;
        }
    }
}
