package com.zcs.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class ContractTest {
    String url = "http://localhost:8000";
    @Test
    public void complieContract(){
        RestTemplate template = new RestTemplate();
        String fileName = "D:\\zcsmart\\zc-test\\src\\test\\java\\programBonus.sol";
        String line = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));){
            int i = 0;
            while (true){
                if(i == 100)break;
                String a = bufferedReader.readLine();
                if(a !=null){
                    line+=a;
                    continue;
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,String> p = new HashMap<>();
        String contractCode = line;
        String contractName = "ProgramBonus";
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        map.put("value","9a8b2aea4240d7d97458ce0a5ae8e8a93d927cea");
        String function = "";
        p.put("code",contractCode);
        p.put("contractName",contractName);
        p.put("function",function);
        p.put("params", JSON.toJSONString(list));
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("data",JSON.toJSONString(p));
        paramMap.put("busi","zctest");
        String result = template.postForObject(url+"/cks/wlt/pbccs.do",paramMap,String.class);
        Map resultMap = JSON.parseObject(result,Map.class);
        log.info("resultMap bin:{}",resultMap.get("bin"));
    }

}
