package com.zcs.test;

import com.zcs.test.mapper.BcAccountMapper;
import com.zcs.test.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

@SpringBootTest
@Slf4j
public class BcAccountMapperTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BcAccountMapper bcAccountMapper;

    @Test
    public void test(){
        Account account = new Account();
        account.setActAddress("123124324234234");
        account.setActNonce("1");
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        bcAccountMapper.insert(account);
    }

}
