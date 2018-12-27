package com.zcs.test.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class ProductMain {
    public static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue();
    public static void main(String[] args) throws InterruptedException {
        new Consume().start();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            add(i+"");
        }
    }
    /**
     * 向队列添加的方法
     * @param ele
     */
    public static void add(String ele) throws InterruptedException {
        queue.put(ele);
    }
    /**
     * 消费者
     */
    private static class Consume extends Thread{
        @Override
        public void run(){
            try {
                while (true) {
                    String ele = queue.take();
                    System.out.println(ele);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
