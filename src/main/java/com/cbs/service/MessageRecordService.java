package com.cbs.service;

import com.cbs.entity.MessageRecord;

import java.util.List;


/**
 * @author : TerryZhang
 * @description：通话记录
 * @date : 2020/2/11
 */
public interface MessageRecordService {

//    MessageRecord findByPhoneNumber(Long telNumber);

    void insertMessageRecord(MessageRecord MessageRecord);

    void insertMessageRecords(List<MessageRecord> MessageRecords);

    List<MessageRecord> findAll();
}
