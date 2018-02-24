package com.zcs.test.mapper;

import com.zcs.test.model.BrandInfo;
import org.apache.ibatis.annotations.Param;

public interface BrandInfoMapper {

    /**
     * 查询品牌信息
     * @param brhId
     * @param brandId
     * @return
     */
    BrandInfo  findByBrhIdAndBrandId(@Param("brhId") String brhId, @Param("brandId") String brandId);
}
