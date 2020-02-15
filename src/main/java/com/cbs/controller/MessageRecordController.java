package com.cbs.controller;

import com.cbs.common.GenerateCall;
import com.cbs.dao.MessageRecordDao;
import com.cbs.entity.MessageRecord;
import com.cbs.entity.MessageRecord;
import com.cbs.service.impl.MessageRecordServiceImpl;
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
@RequestMapping("/messageRecord")
public class MessageRecordController {
    static final Logger logger = LoggerFactory.getLogger(MessageRecordController.class);

    @Autowired
    MessageRecordServiceImpl messageRecordService;
    @Autowired
    MessageRecordDao messageRecordDao;

    @RequestMapping("save")
    public void saveMessageRecord(long callNumber,long calledNumber){
        logger.info("saveMessageRecord");
        MessageRecord messageRecord = new MessageRecord();

        messageRecord.setCalledNumber(callNumber);
        messageRecord.setCallNumber(calledNumber);
        Date sendTime = new Date();
        messageRecord.setSendTime(sendTime);

        messageRecordService.insertMessageRecord(messageRecord);

    }

    @RequestMapping("saveMessageRecords")
    public void saveMessageRecords(int num){
        logger.info("saveMessageRecords");


    }

    @RequestMapping("queryMessageRecords")
    public List<MessageRecord> queryMessageRecords(){
        logger.info("queryMessageRecords");

        return messageRecordService.findAll();
    }
}
