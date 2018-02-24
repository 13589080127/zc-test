package com.zcs.test.service.impl;

import com.zcs.test.service.BussinessService;
import org.springframework.stereotype.Service;

@Service(value = "100000080")
public class JjnBussinessServiceImpl implements BussinessService {

    @Override
    public Object deal(Object o) {
        return "成功";
    }
}
