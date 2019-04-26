package com.zcs.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class GateWayTest {
    String a= "http://localhost:8080/localApps/app/personal/container/online/card/open";

    @Test
    public void test(){
        RestTemplate template = new RestTemplate();
        Map<String,String> parmaMap = new HashMap<>();
        parmaMap.put("brhId","0233103006");
        parmaMap.put("brandId","0001");
        String c = template.postForObject(a,parmaMap, String.class);
        System.out.println(c);
    }
}
