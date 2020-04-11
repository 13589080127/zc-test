package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 用户信息管理
 */
@Service
@Order(0)
@Slf4j
public class AccessCompanyUserHandle extends AccessCompanyHandle {
    /**
     * 处理
     *
     * @return
     */
    @Override
    public void handle(ResultDTO dto) {
        log.info("0");
        //1:判断用户是否存在 不存在报错
        checkUserExist(dto.getUserInfo());
    }
}
