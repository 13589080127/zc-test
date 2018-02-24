package com.zcs.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan("com.zcs.test.mapper")
public class ZcsTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZcsTestApplication.class, args);
	}
}
