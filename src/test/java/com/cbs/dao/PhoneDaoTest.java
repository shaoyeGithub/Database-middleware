package com.cbs.dao;

import com.cbs.common.GenerateTel;
import com.cbs.entity.PhoneNumber;
import com.cbs.service.impl.PhoneNumberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneDaoTest {

    @Autowired
    PhoneDao phoneDao;

    @Autowired
    SegDao segDao;

    @Test
    public void testInsertUser(){
//        phoneDao.insertPhoneNumber(18240442368L,2);
//        phoneDao.insertPhoneNumber(18240442367L, 1);

        segDao.insertSeg(155,4);
    }

    @Test
    public void testSelectMoney(){
        System.out.println(phoneDao.selectMoney(18240442367L,1));
    }

    @Test
    //插入1000条电话信息
    public void registerPhoneNumber(){
        PhoneNumber phoneNumber = new PhoneNumber();
        String tel = GenerateTel.getTel();
        int phone_seg = Integer.parseInt(tel.substring(0,3));
        int seg_id = getPhoneSegID(phone_seg);
        phoneNumber.setPhone_number(Long.parseLong(tel));
        phoneNumber.setSeg_id(seg_id);
        registerPhoneNumber(phoneNumber);
        System.out.println("phone_number" +tel+" seg_id:" + seg_id);
    }

    public void registerPhoneNumber(PhoneNumber phoneNumber){
        System.out.println(phoneDao);
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

}

