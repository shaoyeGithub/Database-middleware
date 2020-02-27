package com.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : TerryZhang
 * @date : 2020/1/7
 */
/*
 设置三种通信套餐 首先计算套餐余量是否够，然后按照计算方式
 */

public class TelPackage implements Serializable {
    private static final long serialVersionUID = -4804144806397193765L;
    private int package_id;
    private int id;
    private int package_money;
    private String charge_unit;
    private int package_data;

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackage_money() {
        return package_money;
    }

    public void setPackage_money(int package_money) {
        this.package_money = package_money;
    }

    public String getCharge_unit() {
        return charge_unit;
    }

    public void setCharge_unit(String charge_unit) {
        this.charge_unit = charge_unit;
    }

    public int getPackage_data() {
        return package_data;
    }

    public void setPackage_data(int package_data) {
        this.package_data = package_data;
    }
}
