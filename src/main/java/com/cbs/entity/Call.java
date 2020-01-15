package com.cbs.entity;

import java.util.Date;

public class Call {
    private long callNumber;
    private long calledNumber;
    private Date callDuration;

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

    public Date getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(Date callDuration) {
        this.callDuration = callDuration;
    }
}
