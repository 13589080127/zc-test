package com.zcs.test.factory;

import com.zcs.test.service.BussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 樊计玉
 */
@Component
public class FactoryService {
    private Map<String,BussinessService> map;
    @Autowired
    public FactoryService(Map<String,BussinessService> map){
        this.map = map;
    }

    public Object execute(String appId,Object o){
        return map.get(appId).deal(o);
    }
}
