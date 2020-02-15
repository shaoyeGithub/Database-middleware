package com.cbs.entity;

import java.util.Date;

public class CallRecord {
    private long callNumber;
    private long calledNumber;
    private Date startTime;
    private Date endTime;
    private long second;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(long callNumber) {
        this.callNumber = callNumber;
    }

    public long getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(long calledNumber) {
        this.calledNumber = calledNumber;
    }

}
