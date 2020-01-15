package com.cbs.controller;

import com.cbs.common.GenerateTel;
import com.cbs.entity.PhoneNumber;
import com.cbs.service.impl.PhoneNumberServiceImpl;
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
    @Autowired
    public PhoneNumberServiceImpl phoneNumberService;

    @RequestMapping("registerPhoneNumber")
    public void insertPhoneNumber(){
        System.out.println("RegisterPhoneNUmber");
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
        System.out.println("queryAllPhoneNumber");
        List<Long> list = phoneNumberService.queryAllPhoneNumber();
        for (Iterator it2 = list.iterator(); it2.hasNext();) {
            System.out.println(it2.next());
        }
    }
    @RequestMapping("queryPhoneNumberMoney")
    public void queryPhoneNumberMoney(){
        System.out.println("queryPhoneNumberMoney");
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhone_number(18204008510L);
        phoneNumber.setSeg_id(1);
        System.out.println("money:"+phoneNumberService.queryMoney(phoneNumber));
    }

    public static void main(String[] args) {
        PhoneNumberController phoneNumberController = new PhoneNumberController();
        phoneNumberController.insertPhoneNumber();
    }
}
