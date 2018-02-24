package com.zcs.test;

import com.alibaba.fastjson.JSON;
import com.zcs.test.model.BrandInfo;
import com.zcs.test.service.BrandInfoService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class ZcsTestApplicationTests extends AbstractTestNGSpringContextTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BrandInfoService brandInfoService;

	@Test
	public void contextLoads() {
		BrandInfo brandInfo = brandInfoService.findByBrhIdAndBrandId("0252103001","0001");
		logger.info("result:{}",JSON.toJSONString(brandInfo));
		Assert.assertNotNull(brandInfo);
	}
}
