package com.zcs.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class FakeDataGen {
    private static RestTemplate restTemplate = new RestTemplate();
    static final String url ="http://localhost:8000";
    public static void main(String[] args) throws InterruptedException {
        Thread reqThread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    ResponseEntity<String> result = restTemplate.getForEntity(url + "/cks/tst/pblte.do", String.class);
                    System.out.println(result.getBody());
                }catch (Exception e){
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        reqThread.start();
    }
}
