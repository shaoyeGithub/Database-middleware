package com.cbs.dao;

import com.cbs.entity.CallRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author : TerryZhang
 * @description：通话记录类
 * @date : 2020/1/7
 */

public interface CallRecordDao extends MongoRepository<CallRecord,Long> {

    CallRecord findByCallNumber(Long callNumber);

}
