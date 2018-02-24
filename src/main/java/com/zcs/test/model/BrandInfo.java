package com.zcs.test.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fjy
 */
@Data
public class BrandInfo implements Serializable{

    private String brhId;

    private String brandId;

    private String brandName;
}
