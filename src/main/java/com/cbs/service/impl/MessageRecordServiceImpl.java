package com.cbs.service.impl;

import com.cbs.dao.MessageRecordDao;
import com.cbs.entity.MessageRecord;
import com.cbs.service.MessageRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TerryZhang
 * @description：通话记录类
 * @date : 2020/2/11
 */
@Service
public class MessageRecordServiceImpl implements MessageRecordService {
    static final Logger logger = LoggerFactory.getLogger(MessageRecordServiceImpl.class);

    @Autowired
    MessageRecordDao messageRecordDao;

//    @Override
//    public MessageRecord findByPhoneNumber(Long telNumber){
//        return MessageRecordDao.findByPhoneNumber(telNumber);
//    }

    @Override
    public void  insertMessageRecord(MessageRecord messageRecord){
        logger.info("insertMessageRecord");
        messageRecordDao.insert(messageRecord);
    }

    @Override
    public void insertMessageRecords(List<MessageRecord> messageRecords){
        logger.info("insertMessageRecords");
        messageRecordDao.insert(messageRecords);
    }

    @Override
    public List<MessageRecord> findAll(){
        logger.info("findAll");
        return messageRecordDao.findAll();
    }
}
