package com.cbs.service.impl;

import com.cbs.dao.MessagePackageDao;
import com.cbs.dao.PackageDao;
import com.cbs.dao.PhoneDao;
import com.cbs.entity.PhoneNumber;
import com.cbs.entity.TelPackage;
import com.cbs.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : TerryZhang
 * @description：套餐实现类
 * @date : 2020/1/23
 */
@Service
public class MessagePackageServiceImpl implements PackageService {
    static final Logger logger = LoggerFactory.getLogger(MessagePackageServiceImpl.class);
    @Autowired
    MessagePackageDao messagepackageDao;

    @Autowired
    PhoneDao phoneDao;

    @Override
    public void queryPackageByPhoneNumber(PhoneNumber phoneNumber) {
        logger.info("queryPackageByPhoneNumber");
        int package_id = phoneDao.selectPackageID(phoneNumber.getPhone_number(),phoneNumber.getSeg_id());
        logger.info("package_id: " + package_id);
        messagepackageDao.selectPackage(package_id);
    }

    @Override
    public List<TelPackage> queryAllPackages() {
        logger.info("queryAllPackages");
        return messagepackageDao.selectPackages();
    }

    @Override
    public TelPackage queryPackageByID(int package_id){
        logger.info("queryPackageByID");
        return messagepackageDao.selectPackage(package_id);
    }
}
