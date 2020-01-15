package com.cbs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Insert("insert into phone_number(phone_number,seg_id,money,package_id) values(#{phone_number},#{seg_id},#{money},#{package_id})")
    void initPhoneNumber(@Param("phone_number") long phone_number, @Param("seg_id") int seg_id,@Param("money")int money,@Param("package_id")int package_id) ;

    @Select("select phone_number from phone_number where seg_id =#{seg_id}")
    List<Long> selectAllPhoneNumber(@Param("seg_id") int seg_id);
}
