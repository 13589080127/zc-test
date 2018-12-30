package com.zcs.test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);
        scheduler.scheduleWithFixedDelay(new Thread(()->{
            System.out.println(1);
        }),10000,3000, TimeUnit.MILLISECONDS);
    }
    //隔多长时间后运行
    private  static Long subdate(){
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();
        c.add(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        System.out.println(c.toString());
        long millis = c.getTimeInMillis() - now;
        return millis;
    }
}
