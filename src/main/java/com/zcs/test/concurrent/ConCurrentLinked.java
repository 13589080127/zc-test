package com.zcs.test.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * 线程安全的队列
 */
public class ConCurrentLinked {
    /**
     * ReentrantLock 此类的构造方法是指的是否使用公平锁
     * 使用独占锁实现的，使用此方法的迭代器会出现若一致性的问题
     */
    private List<String> oneList = new CopyOnWriteArrayList<>();
    /**
     * 读写锁
     */
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    /**
     * jdk 1.8新增的StampedLock
     */
    private static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) {
        //悲观获取读锁
        long readLockStamp = stampedLock.readLock();
        //升级成为写锁
        stampedLock.tryConvertToWriteLock(readLockStamp);
        //解锁
        stampedLock.unlockRead(readLockStamp);
        //写锁
        long writeLockStamp = stampedLock.writeLock();
        stampedLock.unlockWrite(writeLockStamp);
        //乐观读锁
        stampedLock.tryOptimisticRead();


    }




}
