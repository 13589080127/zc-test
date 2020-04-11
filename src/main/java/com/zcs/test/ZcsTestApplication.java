package com.zcs.test;

import com.zcs.test.design.accessDemo.AccessCompanyHandle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import java.util.List;

@SpringCloudApplication
@MapperScan("com.zcs.test.mapper")
public class ZcsTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZcsTestApplication.class, args);
	}




}
