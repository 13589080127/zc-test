package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * 生成pack的
 */
@Service
@Order(2)
@Slf4j
public class AccessCompanyPackHandle extends AccessCompanyHandle {
    /**
     * 业务处理
     *
     * @param resultDTO
     * @return
     */
    @Override
    public void handle(ResultDTO resultDTO) {
        log.info("2");
        // 生成ccksId 取出生成密钥信息
    }
}
