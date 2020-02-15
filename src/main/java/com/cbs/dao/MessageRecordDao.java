package com.cbs.dao;

import com.cbs.entity.CallRecord;
import com.cbs.entity.MessageRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : TerryZhang
 * @description：通话记录类
 * @date : 2020/1/7
 */

public interface MessageRecordDao extends MongoRepository<MessageRecord,Long> {

    MessageRecord findByCallNumber(Long callNumber);

}
