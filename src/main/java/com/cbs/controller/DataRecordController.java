package com.cbs.controller;

import com.cbs.common.GenerateCall;
import com.cbs.dao.DataRecordDao;
import com.cbs.entity.DataRecord;
import com.cbs.service.impl.DataRecordServiceImpl;
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
@RequestMapping("/dataRecord")
public class DataRecordController {
    static final Logger logger = LoggerFactory.getLogger(DataRecordController.class);

    @Autowired
    DataRecordServiceImpl dataRecordService;
    @Autowired
    DataRecordDao dataRecordDao;

    @RequestMapping("save")
    public void saveDataRecord(long phoneNumber,int useDataNum){
        logger.info("saveDataRecord");
        DataRecord dataRecord = new DataRecord();

        dataRecord.setPhoneNumber(phoneNumber);
        Date useTime = new Date();

        dataRecord.setUseTime(useTime);
        dataRecord.setUseDataNum(useDataNum);


        dataRecordService.insertDataRecord(dataRecord);

    }

    @RequestMapping("saveDataRecords")
    public void saveDataRecords(int num){
        logger.info("saveDataRecords");


    }

    @RequestMapping("queryDataRecords")
    public List<DataRecord> queryDataRecords(){
        logger.info("queryDataRecords");

        return dataRecordService.findAll();
    }
}
