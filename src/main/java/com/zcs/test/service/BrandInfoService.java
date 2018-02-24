package com.zcs.test.service;

import com.zcs.test.model.BrandInfo;

/**
 * @author fjy
 */
public interface BrandInfoService {

    /**
     * 查询品牌信息
     * @param brhId
     * @param brandId
     * @return
     */
    BrandInfo findByBrhIdAndBrandId(String brhId,String brandId);
}
