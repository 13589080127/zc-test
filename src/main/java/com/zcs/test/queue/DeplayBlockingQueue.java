package com.zcs.test.queue;

import com.zcs.test.model.Account;
import com.zcs.test.model.BrandInfo;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * 过期队列 优先取出已经过期的元素，放入队列的元素需要继承 Delayed
 */
public class DeplayBlockingQueue {

    private static DelayQueue<Account> delayQueue = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        for(int i = 0;i<20;i++){
            Date newDate = DateUtils.addHours(new Date(),-random.nextInt(3));
            Account account = new Account();
            account.setCreateTime(newDate);
            delayQueue.offer(account);
        }
        while (true){
            System.out.println(delayQueue.take());
        }

    }
}
