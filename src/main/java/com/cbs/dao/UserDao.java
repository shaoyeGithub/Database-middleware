package com.cbs.dao;

import com.cbs.common.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserDao {

    @Insert("insert into user_info(user_id,user_name) values(#{user_id},#{user_name})")
    void insertUser(@Param("user_id") long user_id, @Param("user_name") String user_name) ;


}
