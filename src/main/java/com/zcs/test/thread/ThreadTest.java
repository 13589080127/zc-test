package com.zcs.test.thread;

public class ThreadTest {


    public static class ThisThread extends Thread{
        ThisThread(){
            //设置当前线程为守护线程
            this.setDaemon(true);
            this.start();
        }
        @Override
        public void run(){
            for (;;);
        }
    }

    public static void main(String[] args) {
        new ThisThread();
    }
}
