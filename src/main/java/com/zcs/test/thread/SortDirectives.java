package com.zcs.test.thread;

/**
 * 指令重排序造成的问题
 */
public class SortDirectives {

    public static int num = 0;
    public static volatile boolean ready = false;

    /**
     * 读线程
     */
    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                if(ready){
                    System.out.println(Thread.currentThread().getName()+"  "+(num+num));
                }
            }
        }
    }

    public static class WriteThread extends Thread{
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println(Thread.currentThread().getName()+" deal finish");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        WriteThread writeThread = new WriteThread();
        readThread.start();
        writeThread.start();
        Thread.sleep(10);
        readThread.interrupt();
        System.out.println("main method quit");
    }
}
