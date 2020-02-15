package com.cbs.controller;

import com.cbs.common.GenerateTel;
import com.cbs.entity.PhoneNumber;
import com.cbs.service.impl.PhoneNumberServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：電話號碼控制類
 * @date : 2020/1/8
 */
@RestController
@RequestMapping("/phoneNumber")
public class PhoneNumberController {
    static final Logger logger = LoggerFactory.getLogger(PhoneNumberController.class);
    @Autowired
    public PhoneNumberServiceImpl phoneNumberService;

    @RequestMapping("registerPhoneNumber")
    public void insertPhoneNumber(){
        logger.info("RegisterPhoneNUmber");
        PhoneNumber phoneNumber = new PhoneNumber();
        String tel = GenerateTel.getTel();
        int phone_seg = Integer.parseInt(tel.substring(0,3));
        int seg_id = phoneNumberService.getPhoneSegID(phone_seg);
        phoneNumber.setPhone_number(Long.parseLong(tel));
        phoneNumber.setSeg_id(seg_id);
        System.out.println(phoneNumber);
        phoneNumberService.registerPhoneNumber(phoneNumber);
    }

    @RequestMapping("queryAllPhoneNumber")
    public void queryAllPhoneNumber(){
        logger.info("queryAllPhoneNumber");
        List<Long> list = phoneNumberService.queryAllPhoneNumber();
        for (Iterator it2 = list.iterator(); it2.hasNext();) {
            System.out.println(it2.next());
        }
    }

    @RequestMapping("queryPhoneNumberMoney")
    public int queryPhoneNumberMoney(long telnumber){
        logger.info("queryPhoneNumberMoney");

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone_number(telnumber);
        phoneNumber.setSeg_id(1);

        int queryMoney = phoneNumberService.queryMoney(phoneNumber);
        System.out.println("money:" + queryMoney);

        return queryMoney;
    }

    @RequestMapping("queryPackageID")
    public void queryPackageID(long telnumber){
        logger.info("queryPackageID");
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone_number(telnumber);
        phoneNumber.setSeg_id(1);
        logger.info("money:"+phoneNumberService.queryPackageID(phoneNumber));
    }


    public static void main(String[] args) {
        PhoneNumberController phoneNumberController = new PhoneNumberController();
        phoneNumberController.insertPhoneNumber();
    }
}
