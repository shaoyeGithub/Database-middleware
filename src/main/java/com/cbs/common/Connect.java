package com.cbs.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.*;

@Configuration
//建立连接类
public class Connect {
    static final Logger logger = LoggerFactory.getLogger(Connect.class);

    public Jedis connectRedis(String IP) {
        Jedis jedis = new Jedis(IP, 6379, 100000);
        jedis.auth("123456");
        logger.info("连接redis 成功");
        return jedis;
    }

    public Connection connectMysql(String IP, String databasename) {
        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://" + IP + ":3306/" + databasename + "?useSSL=false&serverTimezone=UTC";

        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "root";

        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }

        System.out.println("Goodbye!");
        return conn;
    }

    @Bean
    public void ConnectSharing() throws IOException, SQLException {
//        通过yaml 方式行不通，有bug 暂时放弃
//        Resource resource = new ClassPathResource("application.yml");
//        File file = resource.getFile();
//
//        DataSource dataSource = new YamlShardingDataSource(file);
//
//        Connection connection = dataSource.getConnection();


        return;
    }


    public static void main(String[] args) {
        System.out.println("213");
    }
}
