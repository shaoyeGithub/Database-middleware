package com.cbs.service;

import com.cbs.entity.DataRecord;

import java.util.List;


/**
 * @author : TerryZhang
 * @description：通话记录
 * @date : 2020/2/11
 */
public interface DataRecordService {

//    DataRecord findByPhoneNumber(Long telNumber);

    void insertDataRecord(DataRecord DataRecord);

    void insertDataRecords(List<DataRecord> DataRecords);

    List<DataRecord> findAll();
}
