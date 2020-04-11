package com.zcs.test.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: fjy
 * @Date: 2020/4/11 11:04
 * @Desc: 三个线程交替打印
 */
public class ThreeThread {
    volatile static int num = 0;
    private static LinkedBlockingQueue<Thread> queue = new LinkedBlockingQueue<>();


    public static void main(String[] args) {
        Thread one = new OnThread(0);
        Thread two = new OnThread(1);
        Thread three = new OnThread(2);
        one.start();
        two.start();
        three.start();
    }

    public static class OnThread extends Thread {
        int name;

        public OnThread(int name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (num <= 100) {
                if (num % 3 == name) {
                    System.out.println("当前线程 ：" + name + " 数字 ： " + num++);
                    LockSupport.unpark(queue.poll());
                } else {
                    queue.offer(this);
                    LockSupport.park();
                }
            }
        }
    }
}
