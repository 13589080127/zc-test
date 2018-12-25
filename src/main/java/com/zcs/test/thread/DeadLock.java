package com.zcs.test.thread;

public class DeadLock {
    public static Object resultA = new Object();
    public static Object resultB = new Object();
    public static void main(String[] args) {
        Thread testThread = new Thread(() -> {
            synchronized (resultA){
                System.out.println(Thread.currentThread().getName()+" ready get resultA");
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" try get resultB");
                    synchronized (resultB){
                        System.out.println(Thread.currentThread().getName()+" ready get resultB");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(()->{
            synchronized (resultB){
                try {
                    System.out.println(Thread.currentThread().getName()+" ready get ResultB");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" try get ResultA");
                    synchronized (resultA){
                        System.out.println(Thread.currentThread().getName()+" ready get resultA");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        testThread.start();
        threadB.start();
        System.out.println("main thread is over");
    }

    /**
     * 按部就班的获取资源时不会发生死锁
     */
    public static class UnDeadLock{
        public static Object resourceA = new Object();
        public static Object resourceB = new Object();
        public static void main(String[] args) {
            Thread threadOne = new Thread(() -> {
                synchronized (resourceA){
                    System.out.println(Thread.currentThread().getName()+" ready get reourceA");
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+" try to get reourceB");
                        synchronized (resourceB){
                            System.out.println(Thread.currentThread().getName()+" ready get reourceB");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread threadTwo = new Thread(() -> {
                synchronized (resourceA){
                    System.out.println(Thread.currentThread().getName()+" ready get reourceA");
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+" try to get reourceB");
                        synchronized (resourceB){
                            System.out.println(Thread.currentThread().getName()+" ready get reourceB");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadOne.start();
            threadTwo.start();
            System.out.println("main is over");
        }
    }
}
