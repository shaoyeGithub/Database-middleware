package com.cbs.service.impl;

import com.cbs.business.object.BusinessObject;
import com.cbs.business.object.BusinessObjectMapper;
import com.cbs.common.GenerateTel;
import com.cbs.dao.PhoneDao;
import com.cbs.entity.PhoneNumber;
import com.cbs.service.PhoneNumberService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：具体实现
 * @date : 2020/1/8
 */
@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {
    @Autowired
    PhoneDao phoneDao;

    @Override
    @Transactional
    @CacheEvict("PhoneNumberList")
    //插入1000条电话信息
    public void registerPhoneNumber(PhoneNumber phoneNumber){
//        System.out.println(phoneDao);
//        phoneDao.insertPhoneNumber(18240442368L,1);
        phoneDao.insertPhoneNumber(phoneNumber.getPhone_number(),phoneNumber.getSeg_id());
    }

    public int getPhoneSegID(int phone_seg){
        if (phone_seg == 182)
            return 1;
        if (phone_seg == 159)
            return 2;
        if (phone_seg == 138)
            return 3;

        return 0;
    }

    @Override
    public void queryPhoneNumber(PhoneNumber number){

    }

    @Override
    public int queryMoney(PhoneNumber number){
        return phoneDao.selectMoney(number.getPhone_number(),number.getSeg_id());
    }

    @Override
    @Cacheable("PhoneNumberList")
    public List<Long> queryAllPhoneNumber(){
        return phoneDao.selectAllPhoneNumber(1);
    }



}
