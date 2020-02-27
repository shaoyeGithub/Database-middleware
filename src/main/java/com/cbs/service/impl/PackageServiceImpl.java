package com.cbs.service.impl;

import com.cbs.common.Log;
import com.cbs.dao.PackageDao;
import com.cbs.dao.PhoneDao;
import com.cbs.entity.PhoneNumber;
import com.cbs.entity.TelPackage;
import com.cbs.service.PackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : TerryZhang
 * @description：套餐实现类
 * @date : 2020/1/23
 */
@Service
public class PackageServiceImpl implements PackageService {
    static final Logger logger = LoggerFactory.getLogger(PackageServiceImpl.class);
    @Autowired
    PackageDao packageDao;

    @Autowired
    PhoneDao phoneDao;

    @Override
    public void queryPackageByPhoneNumber(PhoneNumber phoneNumber) {
        logger.info("queryPackageByPhoneNumber");
        int package_id = phoneDao.selectPackageID(phoneNumber.getPhone_number(),phoneNumber.getSeg_id());
        logger.info("package_id: " + package_id);
        packageDao.selectPackage(package_id);
    }

    @Override
    @Cacheable("packages")
    public List<TelPackage> queryAllPackages() {
        logger.info("queryAllPackages");
        return packageDao.selectPackages();
    }

    @Override
    public TelPackage queryPackageByID(int package_id){
        logger.info("queryPackageByID");
        return packageDao.selectPackage(package_id);
    }
}
