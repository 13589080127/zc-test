package com.zcs.test.design.accessDemo;

import com.zcs.test.design.accessDemo.bean.ResultDTO;
import com.zcs.test.design.accessDemo.bean.UserInfo;

public abstract class AccessCompanyHandle {
    /**
     * 业务处理
     * @param resultDTO
     * @return
     */
    public abstract void handle(ResultDTO resultDTO);

    /**
     * 判断用户是否存在
     * @return
     */
    public boolean checkUserExist(UserInfo userInfo){
        //判断用户是否存在
        return true;
    }
}
