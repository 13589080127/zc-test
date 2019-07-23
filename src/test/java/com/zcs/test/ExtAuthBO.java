package com.zcs.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcs.test.utils.DateUtil;
import com.zcsmart.ccks.SE;
import com.zcsmart.ccks.SEFactory;
import com.zcsmart.ccks.codec.Base64;
import com.zcsmart.ccks.exceptions.SecurityLibExecption;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class ExtAuthBO implements Serializable {
    /**
     * 渠道id
     */
    private String chanId;
    /**
     * 时间戳
     */
    private long ts;
    /**
     * 加密类型
     */
    private String encType;
    /**
     * hashBody
     */
    private String hashBody;
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, SecurityLibExecption {
        SE se = SEFactory.init("/home/csc/thewondernews-server-test.pack",null,null);
        String orderNo = RandomStringUtils.randomAlphanumeric(5);
        DateUtil.getStringDate(new Date(), DateUtil.TIME_FORMATTER_BASIC);
        String a = "{\"totalAmount\":\"99\",\"extRequestTime\":\""+DateUtil.timeLocal("UTC")+"\",\"extOrderNo\":"+orderNo+",\"notifyUrl\":\"www.baidu.com\",\"remark\":\"cs\",\"passbackParams\":\"12313\",\"timeExpire\":\"2019-04-18 17:14:25\",\"timeZone\":\"UTC\",\"subject\":\"下单\",\"merId\":\"999000000000186\",\"currencies\":\"$\",\"h5NotifyUrl\":\"http://habibi.tectc.me:8090/web/wpaycash\"}";
        String body = Base64.encodeBase64String(CommonDealUtil.encryptData(a,se,"0","gateway.zcsmart.com.apps"));
        System.out.println(body);
        ExtAuthBO extAuthBO = new ExtAuthBO();
        extAuthBO.setChanId("8010000008");
        extAuthBO.setHashBody(CommonDealUtil.getBodyHash(body));
        extAuthBO.setTs(System.currentTimeMillis());
        extAuthBO.setEncType("0");
        String json = JSON.toJSONString(extAuthBO);
        System.out.println(json);
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("encData",body);
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");
        header.add("X-APP-AUTHORIZATION",json);
        header.add("X-APP-SIGN",Base64.encodeBase64String(CommonDealUtil.signData(json,se,"0")));
        HttpEntity<Map> entity = new HttpEntity<Map>(paramMap, header);
        ResponseEntity<String> exchange = restTemplate.exchange("http://172.18.90.57:8900/localApps/external/goods/order", HttpMethod.POST,entity,String.class);
//        ResponseEntity<String> exchange = restTemplate.exchange("http://zdgw.legion.shandui.xin/apps/external/goods/order", HttpMethod.POST,entity,String.class);
//        ResponseEntity<String> exchange = restTemplate.exchange("http://gw.legion.vpos.xin/apps/external/goods/order",HttpMethod.POST,entity,String.class);
        System.out.println(JSON.toJSONString(exchange));
        JSONObject jsonObject = JSON.parseObject(exchange.getBody());
        String message = jsonObject.getString("message");
        JSONObject jsonMessage = JSON.parseObject(message);
        String encData = jsonMessage.getString("encData");
        JSONObject result = JSON.parseObject((new String(CommonDealUtil.decode("0",encData,se))));
        System.out.println(result.getString("h5Url"));
    }
}
