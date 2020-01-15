package com.cbs.entity;

public class PhoneNumber {
    private long phone_id;
    private long phone_number;
    private int seg_id;
    private int package_id;
    private int money;



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
