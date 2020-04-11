package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.UserInfo;

public interface UserService {
    /**
     * 用户登录
     * @param userInfo
     */
    void userLogin(UserInfo userInfo);

    /**
     * 用户登录
     * @param userInfo
     */
    void userPreReg(UserInfo userInfo);

    /**
     * 用户登录
     * @param userInfo
     */
    void userReg(UserInfo userInfo);
}
