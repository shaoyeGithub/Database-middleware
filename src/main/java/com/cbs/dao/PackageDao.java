package com.cbs.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author : TerryZhang
 * @date : 2020/1/7
 */
@Mapper
@Component
public interface PackageDao {
    @Insert("")
    void insertPackage();

    @Select("")
    void selectPackage();

    @Update("")
    void  updatePackage();

    @Delete("")
    void deletePackage();

}
