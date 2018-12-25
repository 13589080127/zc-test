package com.zcs.test.model;

import lombok.Data;

import java.util.Date;

@Data
public class Account{
    String actAddress;
    String actNonce;
    String actBalance;
    String actMax;
    String actAcceptMax;
    String actAcceptLimit;
    String storageHex;
    String codeHashHex;
    Date createTime;
    Date updateTime;
}