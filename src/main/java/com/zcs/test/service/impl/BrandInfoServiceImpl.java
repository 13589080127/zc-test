package com.zcs.test.service.impl;

import com.zcs.test.mapper.BrandInfoMapper;
import com.zcs.test.model.BrandInfo;
import com.zcs.test.service.BrandInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * brandinfo
 * @author fjy
 */
@Service
public class BrandInfoServiceImpl implements BrandInfoService {
    @Autowired
    private BrandInfoMapper brandInfoMapper;
    /**
     * 查询品牌信息
     *
     * @param brhId
     * @param brandId
     * @return
     */
    @Override
    public BrandInfo findByBrhIdAndBrandId(String brhId, String brandId) {
        return brandInfoMapper.findByBrhIdAndBrandId(brhId,brandId);
    }
}
