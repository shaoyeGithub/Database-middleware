package com.cbs.dao;

import com.cbs.entity.TelPackage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : TerryZhang
 * @date : 2020/1/7
 */
@Mapper
@Component
public interface MessagePackageDao {
    @Insert("")
    void insertPackage();

    @Select("select * from message_package_info where package_id=#{package_id}")
    TelPackage selectPackage(@Param("package_id") int package_id);

    @Select("select * from message_package_info")
    List<TelPackage> selectPackages();

    @Update("")
    void  updatePackage();

    @Delete("")
    void deletePackage();

}
