package com.zcs.test.design.accessDemo.bean;

public class ResultDTO {

    private CompanyInfo companyInfo;
    private UserInfo userInfo;
    /**
     * 商户号
     */
    private String merId;
    /**
     * 商户名称
     */
    private String merName;
    /**
     * 商户id
     */
    private String chanId;
    /**
     * 结算周期
     */
    private String billTime;
    /**
     * pack 路径
     */
    private String packPath;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getPackPath() {
        return packPath;
    }

    public void setPackPath(String packPath) {
        this.packPath = packPath;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
