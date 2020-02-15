package com.cbs.controller;

import com.cbs.dao.CallRecordDao;
import com.cbs.entity.CallRecord;
import com.cbs.service.impl.CallRecordServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : TerryZhang
 * @description：打电话
 * @date : 2020/2/11
 */
@RestController
@RequestMapping("/call")
public class CallController {
    static final Logger logger = LoggerFactory.getLogger(CallController.class);

    @Autowired
    CallRecordServiceImpl callRecordService;
    @Autowired
    CallRecordDao callRecordDao;

    @RequestMapping("call")
    public void generateCall(long callNumber,long CalledNumber){
        logger.info("generateCall");


    }
}
