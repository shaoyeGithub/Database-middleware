package com.cbs.entity;

import java.util.Date;

/**
 * @author : TerryZhang
 * @description：流量
 * @date : 2020/2/14
 */
public class DataRecord {
    private long phoneNumber;
    private int useDataNum;
    private Date useTime;

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUseDataNum() {
        return useDataNum;
    }

    public void setUseDataNum(int useDataNum) {
        this.useDataNum = useDataNum;
    }
}
