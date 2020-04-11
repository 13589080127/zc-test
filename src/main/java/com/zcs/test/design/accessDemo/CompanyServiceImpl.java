package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompanyServiceImpl extends AbstractCompanyService {
    /**
     * 更新企业状态
     *
     * @param merId
     * @return
     */
    @Override
    public void companyPass(String merId) {
        super.companyPass(merId);
    }

    /**
     * 根据企业id获得企业信息
     *
     * @param merId
     * @return
     */
    @Override
    ResultDTO getCompanyInfo(String merId) {
        return new ResultDTO();
    }
}
