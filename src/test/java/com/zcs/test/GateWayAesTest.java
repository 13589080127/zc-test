package com.zcs.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcs.test.utils.DateUtil;
import com.zcsmart.aes.IAES;
import com.zcsmart.aes.en.AESModule;
import com.zcsmart.aes.impl.AES192Impl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class GateWayAesTest {

    String url = "http://172.18.90.57:8900/localApps/external/goods/order";

    @Test
    public void testAes(){
        IAES iaes = new AES192Impl();
        RestTemplate restTemplate = new RestTemplate();
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();
        String orderNo = generator.generate(5);
        DateUtil.getStringDate(new Date(),DateUtil.TIME_FORMATTER_BASIC);
        String param = "{\"totalAmount\":\"99\",\"extRequestTime\":\""+ DateUtil.timeLocal("UTC")+"\",\"extOrderNo\":"+orderNo+",\"notifyUrl\":\"www.baidu.com\",\"remark\":\"cs\",\"passbackParams\":\"12313\",\"timeExpire\":\"2019-04-18 17:14:25\",\"timeZone\":\"UTC\",\"subject\":\"下单\",\"merId\":\"999000000000186\",\"currencies\":\"$\",\"h5NotifyUrl\":\"http://habibi.tectc.me:8090/web/wpaycash\"}";
        String encBody = iaes.encDataBase64(param, AESModule.AES_192_CBC_PKCS7,"1234567890098765","1234567890098765");
        ExtAuthBO extAuthBO = new ExtAuthBO();
        extAuthBO.setChanId("8010000014");
        extAuthBO.setHashBody(iaes.signData(encBody));
        extAuthBO.setTs(System.currentTimeMillis());
        extAuthBO.setEncType("1");
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("encData",encBody);
        String json = JSON.toJSONString(extAuthBO);
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");
        header.add("X-APP-AUTHORIZATION", json);
        header.add("X-APP-SIGN", iaes.signData(json));
        HttpEntity<Map> entity = new HttpEntity<>(paramMap, header);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST,entity,String.class);
        System.out.println(JSON.toJSONString(exchange));
        JSONObject jsonObject = JSON.parseObject(exchange.getBody());
        String message = jsonObject.getString("message");
        JSONObject jsonMessage = JSON.parseObject(message);
        String encData = jsonMessage.getString("encData");
        String auth = exchange.getHeaders().getFirst("X-APP-AUTHORIZATION").toString();

        String resultSign = exchange.getHeaders().getFirst("X-APP-SIGN").toString();

        String resultHashBody = JSON.parseObject(auth).getString("hashBody");
        log.info("hashBody checkResult : {}",iaes.signCheck(encData,resultHashBody));
        log.info("sign checkResult : {}",iaes.signCheck(auth,resultSign));
        String result = iaes.decDataBase64(encData,AESModule.AES_192_CBC_PKCS7,"1234567890098765","1234567890098765");
        log.info("decData result :{}",result);
    }
}
