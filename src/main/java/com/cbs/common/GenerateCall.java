package com.cbs.common;

import com.cbs.entity.CallRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：生成打电话信息
 * @date : 2020/1/7
 */
public class GenerateCall {
    private long callNumber;
    private long calledNumber;
    private Date startTime;
    private Date endTime;
    private long second;

    public CallRecord getOneRecord(){
        CallRecord callRecord = new CallRecord();
        callNumber = Long.parseLong(GenerateTel.getTel());
        calledNumber = Long.parseLong(GenerateTel.getTel());
        startTime = GenerateTime.getTime();
        endTime = GenerateTime.getEndTime(startTime);
        second = GenerateTime.getTimeSub(startTime,endTime);

        callRecord.setCalledNumber(calledNumber);
        callRecord.setCallNumber(callNumber);
        callRecord.setStartTime(startTime);
        callRecord.setEndTime(endTime);
        callRecord.setSecond(second);

        return callRecord;
    }

    public List<CallRecord> getRecords(int nums){
        List<CallRecord> callRecords = new ArrayList<>();
        for (int i = 0; i < nums; i++) {
            CallRecord callRecord =  getOneRecord();
            callRecords.add(callRecord);
        }
        return callRecords;
    }
    public void printRecords(List<CallRecord> list){
        for (Iterator<CallRecord> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) {
        GenerateCall generateCall = new GenerateCall();
        generateCall.printRecords(generateCall.getRecords(10));
    }
}
