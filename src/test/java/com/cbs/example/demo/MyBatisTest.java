package com.cbs.example.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class MyBatisTest {
    /** * MyBatis SqlSessionFactory * SqlSessionFactory????SqlSession????????????SqlSession??????????commit?rollback?close???? * @return */ private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "configuration.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(
                    resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
//        SqlSession sqlSession = getSessionFactory().openSession();
//        BusinessObjectMapper BusinessObjectMapper = sqlSession.getMapper(BusinessObjectMapper.class);
//        BusinessObject businessObject = BusinessObjectMapper.findByBOID(1);
//        System.out.println("连接成功了？");
//        System.out.println(businessObject.getBusinessObjectName());
    }
}

