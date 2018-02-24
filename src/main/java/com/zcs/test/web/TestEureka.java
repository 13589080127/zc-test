package com.zcs.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fjy
 */
@RestController
public class TestEureka {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/list")
    public String list(){
        logger.info("list method is call");
        return "list success";
    }
}
