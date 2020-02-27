package com.cbs.entity;

import java.io.Serializable;

public class PhoneNumber implements Serializable {
    private static final long serialVersionUID = -5010118025280186307L;
    private long phone_id;
    private long phone_number;
    private int seg_id;
    private int package_id;
    private int money;
    private int all_consume_money;

    public int getAllConsumeMoney() {
        return all_consume_money;
    }

    public void setAllConsumeMoney(int all_consume_money) {
        this.all_consume_money = all_consume_money;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public int getSeg_id() {
        return seg_id;
    }

    public void setSeg_id(int seg_id) {
        this.seg_id = seg_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }



    public long getPhone_id() {
        return phone_id;
    }
}
