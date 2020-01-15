package com.cbs.business.object;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
// 一个BO 对应数据库中的一张表，所以一个任务可以拆分成多个BO
public class BusinessObject {
    private   int businessObejctID ;
    private   String businessObjectName = ""; //对应到
    private   String operation;
    //Queue<String> fields = new LinkedList<String>(); //字段名字
    // 字段名字以及数据
    Map<String, String > data = new HashMap<String, String >();

    public static void main(String args[]) {
        System.out.println("BusinessObject");
    }

    public String getBusinessObjectName() {
        //System.out.println(this.businessObjectName);
        return businessObjectName;
    }


    public  void setBusinessObjectName(String businessObjectName) {
        this.businessObjectName = businessObjectName;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public  String getOperation() {
        return operation;
    }

    public  void setOperation(String operation) {
        this.operation = operation;
    }

    public int getBusinessObejctID() {
        return businessObejctID;
    }

    public void setBusinessObejctID(int businessObejctID) {
        this.businessObejctID = businessObejctID;
    }
}
