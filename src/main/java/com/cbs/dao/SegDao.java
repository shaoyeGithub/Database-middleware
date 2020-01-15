package com.cbs.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author : TerryZhang
 * @description：号段类
 * @date : 2020/1/7
 */
@Mapper
@Component
public interface SegDao {
    @Insert("insert into phone_seg(phone_seg,seg_id) values(#{phone_seg},#{seg_id})")
    void insertSeg(@Param("phone_seg") int phone_seg,@Param("seg_id") int seg_id);

    @Select("select seg_id from phone_seg where phone_seg=#{phone_seg}")
    int selectSeg(@Param("phone_seg") int phone_seg);

    @Update("")
    void  updateSeg();

    @Delete("")
    void deleteSeg();
}
