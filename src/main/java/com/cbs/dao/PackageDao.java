package com.cbs.dao;

import com.cbs.cache.RedisCache;
import com.cbs.entity.TelPackage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : TerryZhang
 * @date : 2020/1/7
 */
@Mapper
@Component
public interface PackageDao {
    @Insert("")
    void insertPackage();

    @Select("select * from package_info where package_id=#{package_id}")
    TelPackage selectPackage(@Param("package_id") int package_id);

    @Select("select * from package_info")
    List<TelPackage> selectPackages();

    @Update("")
    void  updatePackage();

    @Delete("")
    void deletePackage();

}
