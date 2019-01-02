package com.zcs.test.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fjy
 */
@Data
public class BrandInfo implements Serializable,Comparable{

    private String brhId;

    private String brandId;

    private String brandName;

    private Integer count;

    @Override
    public int compareTo(Object o) {
        BrandInfo brandInfo = (BrandInfo) o;
        return brandInfo.getCount().compareTo(count);
    }
}
