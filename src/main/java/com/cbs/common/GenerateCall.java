package com.cbs.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：生成打电话信息
 * @date : 2020/1/7
 */
public class GenerateCall {
    private String callNumber;
    private String calledNumber;
    private String startTime;
    private String endTime;

    public String getOneRecord(){
        callNumber = GenerateTel.getTel();
        calledNumber = GenerateTel.getTel();
        startTime = GenerateTime.getTime();
        endTime = GenerateTime.getEndTime(startTime);

        String res = "callNumber:" + callNumber+" calledNumber:"+calledNumber+" startTime:"+startTime+" endTime:"+ endTime;
        return  res;
//        System.out.println(callNumber);
//        System.out.println(calledNumber);
//        System.out.println(startTime);
//        System.out.println(endTime);
    }

    public List<String> getRecords(int nums){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            String str =  getOneRecord();
            list.add(str);
        }
        return list;
    }
    public void printRecords(List<String> list){
        for (Iterator<String> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) {
        GenerateCall generateCall = new GenerateCall();
        generateCall.printRecords(generateCall.getRecords(10));
    }
}
