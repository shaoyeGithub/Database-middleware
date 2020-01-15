package com.cbs.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author : TerryZhang
 * @description：通话记录类
 * @date : 2020/1/7
 */
@Mapper
@Component
public interface CallRecordDao {
    @Insert("")
    void insertCallRecord();

    @Select("")
    void selectCallRecord();

    @Update("")
    void  updateCallRecord();

    @Delete("")
    void deleteCallRecord();
}
