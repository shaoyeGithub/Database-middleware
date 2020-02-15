package com.cbs.controller;

import com.cbs.common.Log;
import com.cbs.entity.PhoneNumber;
import com.cbs.entity.TelPackage;
import com.cbs.service.impl.PackageServiceImpl;
import com.cbs.service.impl.PhoneNumberServiceImpl;
import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * @author : TerryZhang
 * @description：套餐计算类
 * @date : 2020/1/23
 */
@RestController
@RequestMapping("/consume")
public class ConsumeController {
    static final Logger logger = LoggerFactory.getLogger(ConsumeController.class);
    @Autowired
    PackageServiceImpl packageService;

    @Autowired
    PhoneNumberServiceImpl phoneNumberService;

    //计算金额
    @RequestMapping("consumeMoney")
    public int  consumeMoney(long callTelNumber,long CalledTelNumber,int time){
        logger.info("consumeMoney");
        if(callTelNumber == 119 ||CalledTelNumber ==120) {
            logger.info("Dont need consume money!");
        }
        PhoneNumber number = phoneNumberService.queryPhoneNumber(callTelNumber);

        if (number.getMoney() <0){
            logger.info("Money is not enough！");
            return -1;
        }
        TelPackage telPackage = queryPackageByPhoneNumber(callTelNumber);
        logger.info("chargeUnit:" + telPackage.getCharge_unit());

        String [] unit = telPackage.getCharge_unit().split("/");
        int consumeMoney = Integer.parseInt(unit[0]) * time;

        phoneNumberService.updateMoney(number,consumeMoney);
        return 0;
    }

    //查询套餐
    @RequestMapping("queryPackageByPhoneNumber")
    public TelPackage queryPackageByPhoneNumber(long telNumber){
        logger.info("queryPackageByPhoneNumber");
        PhoneNumber phoneNumber = new PhoneNumber();
//        phoneNumber.setPhone_number(18200562083L);
        phoneNumber.setPhone_number(telNumber);
        phoneNumber.setSeg_id(1);

        //查询packageID
        int package_id = phoneNumberService.queryPackageID(phoneNumber);
        TelPackage telPackage = packageService.queryPackageByID(package_id);
        logger.info("telPackage.money: "+ telPackage.getPackage_money());
        return telPackage;
    }

    @RequestMapping("queryPackageByID")
    public void queryPackageByID(){
        TelPackage telPackage = packageService.queryPackageByID(1);
        logger.info("telPackage.money: "+ telPackage.getPackage_money());
    }

    @RequestMapping("queryAllPackages")
    public void queryAllPackages(){
        logger.info("queryAllPackages");
        List<TelPackage> telPackages = packageService.queryAllPackages();
        for (Iterator it2 = telPackages.iterator(); it2.hasNext();) {
            TelPackage telPackage = (TelPackage) it2.next();
            System.out.println(telPackage.getPackage_money());
        }
    }
}
