package com.cbs.dao;

import com.cbs.cache.RedisCache;
import com.cbs.entity.PhoneNumber;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component(value ="PhoneDao")

public interface PhoneDao {
    @Insert("insert into phone_number(phone_number,seg_id) values(#{phone_number},#{seg_id})")
    void insertPhoneNumber(@Param("phone_number") long phone_number, @Param("seg_id") int seg_id) ;

    @Select("select money from phone_number where phone_number=#{phone_number} and seg_id =#{seg_id}")
    int selectMoney(@Param("phone_number") long phone_number,@Param("seg_id") int seg_id);

    @Select("select package_id from phone_number where phone_number=#{phone_number} and seg_id =#{seg_id}")
    int selectPackageID(@Param("phone_number") long phone_number,@Param("seg_id") int seg_id);

    @Insert("insert into phone_number(phone_number,seg_id,money,package_id) values(#{phone_number},#{seg_id},#{money},#{package_id})")
    void initPhoneNumber(@Param("phone_number") long phone_number, @Param("seg_id") int seg_id,@Param("money")int money,@Param("package_id")int package_id) ;

    @Select("select phone_number from phone_number where seg_id =#{seg_id}")
    List<Long> selectAllPhoneNumber(@Param("seg_id") int seg_id);

    @Update("update phone_number set money=#{money} where phone_number=#{phone_number}")
    void updateMoney(@Param("money") int money,@Param("phone_number") long phone_number);

    @Select("select * from phone_number where phone_number=#{phone_number}")
    PhoneNumber selectPhoneNumber(@Param("phone_number") long phone_number);
}
