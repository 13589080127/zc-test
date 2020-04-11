package com.zcs.test;

import com.zcs.test.design.accessDemo.AccessCompanyHandle;
import com.zcs.test.design.accessDemo.CompanyService;
import com.zcs.test.design.accessDemo.UserService;
import com.zcs.test.design.accessDemo.bean.ResultDTO;
import com.zcs.test.design.accessDemo.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;
@SpringBootTest
@Slf4j
public class TestAccessCompanyHandle extends AbstractTestNGSpringContextTests {
    @Autowired
    private List<AccessCompanyHandle> companyHandles;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Test
    public void applyTestParam(){
        for (AccessCompanyHandle companyHandle : companyHandles){
            companyHandle.handle(new ResultDTO());
        }
    }
    @Test
    public void updateMerStatus(){
        companyService.companyPass("9999999");
    }
    @Test
    public void userLogin(){
        userService.userLogin(new UserInfo());
    }

    @Test
    public void userPreReg(){
        userService.userPreReg(new UserInfo());
    }

    @Test
    public void userReg(){
        userService.userReg(new UserInfo());
    }
}
