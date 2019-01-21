package com.zcs.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class Trans107Test extends AbstractTestNGSpringContextTests {
    String toAddress = "c0ba9abcc9f943ce4e291c6530477e57637681f0";
    String mainAddress = "e0c7d2a75ccd61af87aaa818cdcb2246706363a1";
    String txHash = "";
    String amount = "100";//单位（个）
    String url = "http://172.18.92.107:38000";
    @Test
    public void trans() throws InterruptedException {
        Map<String,String> readyMap = new HashMap<>();
        readyMap.put("f4fcdf9e5603a5375f9fe0c8bba951e085cdcdfc","1a7264ae5078f2a0c5b5e567457bbcedf1bdc3263ad32576c9a3c512c386ed6f");
        readyMap.put("8502208b949e7c08acb4c2f1aef06caa39891a79","fe420ae439f8d76999dd9e784ab87c400c04029076f9d22044d20d9bd1277e54");
        readyMap.put("3eb9c18610e35c7f039d3591a15f967d98b3d5b8","5e6517749743b5c1d2f16dcf411d521dabe73933d4cfc5225b53e3113e62990b");
        readyMap.put("3db85d2797ddc2a4802e9fff1a0232b0bbf06c8a","d3b23e124199532c16fb4a940b5faf6ff6615479734e3decba4e644428bbba5d");
        readyMap.put("ad081e743aa6174e4d2609fc8b0985718e77082d","e7d9b59ff26536531c0e502b7ddcd222df8c8c01a90dfe03e136704d93c3fea3");
        readyMap.put("b16657a7bf154da2aab8dc9a3484e9413c165ff1","fc1d181a38ebc2ccc0c0d4c605937de17bc5fdb347a9621b74c544463d02e3cb");
        RestTemplate restTemplate = new RestTemplate();
        String path = "/cks/tst/pbtua.do";
        url +=path;
        for(String address:readyMap.keySet()){
            //Thread.sleep(4000);
            String pri = readyMap.get(address);
            Map<String,String> paramMap = new HashMap<>();
            paramMap.put("unionAccountAddress",mainAddress);
            paramMap.put("amount",new BigDecimal(amount).multiply(new BigDecimal(10).pow(18)).toString());
            paramMap.put("relAddress",address);
            paramMap.put("relKey",pri);
            paramMap.put("relTxHash",txHash);
            paramMap.put("toAddress",toAddress);
            String resultString = restTemplate.postForObject(url,paramMap,String.class);
            log.info("转账结果：{}",resultString);
            Map<String,Object> resultMap = JSON.parseObject(resultString,Map.class);
            if((Integer)resultMap.get("retCode") == 1){
                String hash = resultMap.get("retMsg").toString();
                if(StringUtils.isBlank(txHash)){
                    txHash = hash;
                }
            }else{
                log.error("转账失败 address:{},hash:{} ",address,txHash);
                break;
            }
        }
        log.info("转账结束===========");
    }
    public static void main(String[] args) {

        try{
            if(1==1) {
                System.out.println("1==1");
                return;
            }

        }finally {
            System.out.println("finally");
        }
        System.out.println("main is end");
    }
}
