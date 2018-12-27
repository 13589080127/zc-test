package com.zcs.test;

import com.zcs.test.mapper.BcAccountMapper;
import com.zcs.test.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
@Slf4j
public class BcAccountMapperTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BcAccountMapper bcAccountMapper;
    private static ReentrantLock reentrantLock = new ReentrantLock(true);
    private static Condition condition = reentrantLock.newCondition();

    private static volatile int a = 0;
    @Test
    public void test(){
        Account account = new Account();
        account.setActAddress("123124324234234");
        account.setActNonce("1");
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        bcAccountMapper.insert(account);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0;i<3;i++){
            new LockTest().start();
        }
        Thread.sleep(15000);
        System.out.println(a);
    }

    static class LockTest extends Thread{
        @Override
        public void run(){
            try{
                System.out.println(Thread.currentThread().getName()+"正在等待锁 a="+a);
                reentrantLock.lock();
                System.out.println("reentrantLock.lock(a) next  a= "+a);
                if(a ==0){
                    System.out.println("睡觉之前a的值 ="+a);
                    Thread.sleep(3000);
                    a++;
                    System.out.println("现在a的值 ="+a);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName()+"开始释放锁");
                reentrantLock.unlock();
            }
        }
    }
}
