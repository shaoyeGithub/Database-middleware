package com.cbs.entity;

import java.util.Date;

/**
 * @author : TerryZhang
 * @date : 2020/1/7
 */
public class Package {
    private String packageID;
    private String packageInfo;
    private Date packageStartTime;
    private Date packageEndTime;

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(String packageInfo) {
        this.packageInfo = packageInfo;
    }

    public Date getPackageStartTime() {
        return packageStartTime;
    }

    public void setPackageStartTime(Date packageStartTime) {
        this.packageStartTime = packageStartTime;
    }

    public Date getPackageEndTime() {
        return packageEndTime;
    }

    public void setPackageEndTime(Date packageEndTime) {
        this.packageEndTime = packageEndTime;
    }
}
