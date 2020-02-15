package com.cbs.controller;

import com.cbs.common.GenerateCall;
import com.cbs.dao.CallRecordDao;
import com.cbs.entity.CallRecord;
import com.cbs.service.impl.CallRecordServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：通话记录类
 * @date : 2020/2/11
 */
@RestController
@RequestMapping("/callrecord")
public class CallRecordController {
    static final Logger logger = LoggerFactory.getLogger(CallRecordController.class);

    @Autowired
    CallRecordServiceImpl callRecordService;
    @Autowired
    CallRecordDao callRecordDao;

    @RequestMapping("save")
    public void saveCallRecord(long callNumber,long calledNumber,long second){
        logger.info("saveCallRecord");
        CallRecord callRecord = new CallRecord();

        callRecord.setCalledNumber(callNumber);
        callRecord.setCallNumber(calledNumber);
        Date endTime = new Date();

        Date startTime = new Date (endTime.getTime() + second);
        callRecord.setStartTime(startTime);
        callRecord.setEndTime(endTime);
        callRecord.setSecond(second);

        callRecordService.insertCallRecord(callRecord);

    }

    @RequestMapping("saveCallRecords")
    public void saveCallRecords(int num){
        logger.info("saveCallRecords");

        GenerateCall generateCall = new GenerateCall();
        List<CallRecord> callRecords = generateCall.getRecords(num);

        callRecordService.insertCallRecords(callRecords);
    }

    @RequestMapping("queryCallRecords")
    public List<CallRecord> queryCallRecords(){
        logger.info("queryCallRecords");

        return callRecordService.findAll();
    }
}
