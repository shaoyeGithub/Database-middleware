package com.cbs.entity;

import java.util.Date;

public class DataFlow {
    private long useNumber;
    private int session;
    private Date sendTime;

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public long getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(long useNumber) {
        this.useNumber = useNumber;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
}
