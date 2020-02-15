package com.cbs.dao;

import com.cbs.entity.CallRecord;
import com.cbs.entity.DataRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : TerryZhang
 * @description：通话记录类
 * @date : 2020/1/7
 */

public interface DataRecordDao extends MongoRepository<DataRecord,Long> {

    DataRecord findByPhoneNumber(Long phoneNumber);

}
