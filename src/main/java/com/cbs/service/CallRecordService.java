package com.cbs.service;

import com.cbs.entity.CallRecord;

import java.util.List;


/**
 * @author : TerryZhang
 * @description：通话记录
 * @date : 2020/2/11
 */
public interface CallRecordService {

//    CallRecord findByPhoneNumber(Long telNumber);

    void insertCallRecord(CallRecord callRecord);

    void insertCallRecords(List<CallRecord> callRecords);

    List<CallRecord> findAll();
}
