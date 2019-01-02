package com.zcs.test.model;

import lombok.Data;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
public class Account implements Delayed {
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

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.toMillis(createTime.getTime());
    }

    @Override
    public int compareTo(Delayed o) {
        Account account = (Account) o;
        return account.getCreateTime().compareTo(getCreateTime());
    }
}