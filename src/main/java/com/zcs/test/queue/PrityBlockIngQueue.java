package com.zcs.test.queue;

import com.zcs.test.model.BrandInfo;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级队列
 */
public class PrityBlockIngQueue {
    /**
     * 默认初始化大小是11个长度，队列会在使用的时候自己进行扩容
     * 添加的元素 需要实现Comparable 接口然后重写 compareTo方法
     */
    private static PriorityBlockingQueue<BrandInfo> priorityBlockingQueue = new PriorityBlockingQueue();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        for(int i = 0;i<20;i++){
            BrandInfo brandInfo = new BrandInfo();
            brandInfo.setCount(random.nextInt(10));
            priorityBlockingQueue.offer(brandInfo);
        }
        while (true){
            System.out.println(priorityBlockingQueue.take());
        }
    }
}
