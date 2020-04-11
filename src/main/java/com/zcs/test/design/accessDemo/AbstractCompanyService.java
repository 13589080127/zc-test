package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractCompanyService implements CompanyService{
    @Autowired
    private List<AccessCompanyHandle> accessCompanyHandles;
    /**
     * 更新企业状态
     */
    public void companyPass (String merId){
        ResultDTO resultDTO = getCompanyInfo(merId);
        for(AccessCompanyHandle accessCompanyHandle : accessCompanyHandles){
            accessCompanyHandle.handle(resultDTO);
        }
    }

    /**
     * 根据企业id获得企业信息
     * @param merId
     * @return
     */
    abstract ResultDTO getCompanyInfo(String merId);
}
