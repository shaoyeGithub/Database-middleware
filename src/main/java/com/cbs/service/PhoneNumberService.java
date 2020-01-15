package com.cbs.service;


import com.cbs.entity.PhoneNumber;

import java.util.List;


/**
 * @author : TerryZhang
 * @description：电话号码实现接口
 * @date : 2020/1/8
 */
public interface PhoneNumberService {

    //注册电话
    void registerPhoneNumber(PhoneNumber number);

    //查询具体电话
    void queryPhoneNumber(PhoneNumber number);

    // 查询话费
    int queryMoney(PhoneNumber number);

    //查询所有电话
    List<Long> queryAllPhoneNumber();
}
