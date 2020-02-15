package com.cbs.service.impl;

import com.cbs.dao.DataRecordDao;
import com.cbs.entity.DataRecord;
import com.cbs.service.DataRecordService;
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
public class DataRecordServiceImpl implements DataRecordService {
    static final Logger logger = LoggerFactory.getLogger(DataRecordServiceImpl.class);

    @Autowired
    DataRecordDao dataRecordDao;

//    @Override
//    public DataRecord findByPhoneNumber(Long telNumber){
//        return DataRecordDao.findByPhoneNumber(telNumber);
//    }

    @Override
    public void  insertDataRecord(DataRecord dataRecord){
        logger.info("insertDataRecord");
        dataRecordDao.insert(dataRecord);
    }

    @Override
    public void insertDataRecords(List<DataRecord> dataRecords){
        logger.info("insertDataRecords");
        dataRecordDao.insert(dataRecords);
    }

    @Override
    public List<DataRecord> findAll(){
        logger.info("findAll");
        return dataRecordDao.findAll();
    }
}
