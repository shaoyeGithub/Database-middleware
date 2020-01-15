package com.cbs.dao;

import com.cbs.dao.SegDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : TerryZhang
 * @description：号段查询测试类
 * @date : 2020/1/8
 */
@SpringBootTest
public class SegDaoTest {
    @Autowired
    SegDao segDao;

    @Test
    public void testInsertPhoneSeg(){
        segDao.insertSeg(182,1);
        segDao.insertSeg(159,2);
        segDao.insertSeg(138,3);
    }

    @Test
    public void testSelectPhoneSeg(){
        System.out.println("seg_id:"+segDao.selectSeg(138));
        System.out.println("seg_id:"+segDao.selectSeg(159));

    }
}
