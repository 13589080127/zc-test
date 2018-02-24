package com.zcs.test.service.impl;

import com.zcs.test.service.BussinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "1001")
public class JdBussinessServiceImpl implements BussinessService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Object deal(Object o) {
        logger.debug("简兑请求");
        return "简兑请求";
    }
}
