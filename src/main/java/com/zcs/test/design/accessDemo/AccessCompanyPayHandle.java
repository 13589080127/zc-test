package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 绑定支付活动的
 */
@Service
@Order(3)
@Slf4j
public class AccessCompanyPayHandle extends AccessCompanyHandle {
    /**
     * 业务处理
     *
     * @param resultDTO
     * @return
     */
    @Override
    public void handle(ResultDTO resultDTO) {
        log.info("3");
    }
}
