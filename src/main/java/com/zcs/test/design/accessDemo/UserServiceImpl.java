package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends AccessCompanyUserHandle implements UserService{
    /**
     * 用户登录
     *
     * @param userInfo
     */
    @Override
    public void userLogin(UserInfo userInfo) {
        checkUserExist(userInfo);
    }

    /**
     * 用户预注册
     *
     * @param userInfo
     */
    @Override
    public void userPreReg(UserInfo userInfo) {
        checkUserExist(userInfo);
    }

    /**
     * 用户注册
     *
     * @param userInfo
     */
    @Override
    public void userReg(UserInfo userInfo) {
        checkUserExist(userInfo);
    }
}
