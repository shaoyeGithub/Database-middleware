package com.cbs.service;

import com.cbs.entity.PhoneNumber;
import com.cbs.entity.TelPackage;

import java.util.List;

/**
 * @author : TerryZhang
 * @description：套餐类
 * @date : 2020/1/23
 */
public interface DataPackageService {

    //查询套餐
    void queryPackageByPhoneNumber(PhoneNumber phoneNumber);

    TelPackage queryPackageByID(int package_id);

    //查询所有套餐
    List<TelPackage> queryAllPackages();


}
