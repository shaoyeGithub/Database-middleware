package com.cbs.service.impl;

import com.cbs.controller.CallRecordController;
import com.cbs.dao.CallRecordDao;
import com.cbs.entity.CallRecord;
import com.cbs.service.CallRecordService;
import jdk.nashorn.internal.codegen.CompilerConstants;
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
public class CallRecordServiceImpl implements CallRecordService {
    static final Logger logger = LoggerFactory.getLogger(CallRecordServiceImpl.class);

    @Autowired
    CallRecordDao callRecordDao;

//    @Override
//    public CallRecord findByPhoneNumber(Long telNumber){
//        return callRecordDao.findByPhoneNumber(telNumber);
//    }

    @Override
    public void  insertCallRecord(CallRecord callRecord){
        logger.info("insertCallRecord");
        callRecordDao.insert(callRecord);
    }

    @Override
    public void insertCallRecords(List<CallRecord> callRecords){
        logger.info("insertCallRecords");
        callRecordDao.insert(callRecords);
    }

    @Override
    public List<CallRecord> findAll(){
        logger.info("findAll");
        return callRecordDao.findAll();
    }
}
