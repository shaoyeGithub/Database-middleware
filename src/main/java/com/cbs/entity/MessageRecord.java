package com.cbs.entity;

import java.util.Date;

/**
 * @author : TerryZhang
 * @description：短信类
 * @date : 2020/2/14
 */
public class MessageRecord {
    private long calledNumber;
    private long callNumber;
    private Date sendTime;

    public long getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(long calledNumber) {
        this.calledNumber = calledNumber;
    }

    public long getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(long callNumber) {
        this.callNumber = callNumber;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
