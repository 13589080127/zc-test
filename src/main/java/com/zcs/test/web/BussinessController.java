package com.zcs.test.web;
import com.alibaba.fastjson.JSON;
import com.zcs.test.factory.FactoryService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;

/**
 * @author 樊计玉
 */
@RestController
public class BussinessController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FactoryService factoryService;
    @RequestMapping(value = "/bussiness")
    public String bussiness(HttpServletRequest request, @RequestBody ModelMap modelMap){
        logger.debug("请求进入：{}", JSON.toJSONString(modelMap));
        logger.debug("debug level log");
        logger.info("info level log");
        logger.warn("warn level log");
        logger.error("error level log");
        System.out.println(JSON.toJSONString(modelMap));
        String appId = request.getHeader("app_id");
        return (String)factoryService.execute(appId,modelMap);
    }
}
