package com.zcs.test.queue;

import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: fjy
 * @Date: 2020/4/11 13:13
 * @Desc:
 */
public class CountDownLatchThread {
    static RestTemplate restTemplate = new RestTemplate();
    static CountDownLatch countDownLatch = new CountDownLatch(100);
    static ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            executor.execute(new TestThread());
            countDownLatch.countDown();
        }
    }


    public static class TestThread extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            countDownLatch.await();
            String result = restTemplate.postForObject("https://www.baidu.com", "", String.class);
            System.out.println(result);

        }
    }
}
