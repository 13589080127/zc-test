package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 企业信息管理
 */
@Service
@Order(1)
@Slf4j
public class AccessCompanySaveHandle extends AccessCompanyHandle {
    /**
     * 业务处理
     *
     * @param resultDTO
     * @return
     */
    @Override
    public void handle(ResultDTO resultDTO) {
        resultDTO.getCompanyInfo();
        log.info("1");
        // 保存商户信息
    }
}
