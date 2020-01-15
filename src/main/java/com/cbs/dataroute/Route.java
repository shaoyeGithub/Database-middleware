package com.cbs.dataroute;
import com.cbs.common.Connect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.util.Map;

public class Route {
    // 获取到电话号码
    public static int phoneNumber;
    static final Logger logger = LoggerFactory.getLogger(Route.class);
    Connect connect = new Connect();

    String getPrefixByPhoneNumber(int phoneNumber){
        String str = phoneNumber + "";
        String prefix = str.substring(0,3);
        logger.info("获取号码前缀:" + prefix);
        return prefix;
    }

    // 查询路由数据库获取 号码host以及数据库表名
    String getDatabaseByPhoneNumber(int phoneNumber){
        String prefix = getPrefixByPhoneNumber(phoneNumber);


        Jedis jedis = connect.connectRedis("127.0.0.1");

        Map<String, String> properties = jedis.hgetAll("phonePrefix");
        logger.info("获取号段:");
        logger.info(properties.keySet().toString());
//        System.out.println(jedis.hmget());

        for(String str :properties.keySet()) {
            //logger.info(str);
            //System.out.println(prefix);
            if(prefix.equals(str)) {
                logger.info("数据库host和 IP："+properties.get(str));
                return properties.get(str);
            }

        }
        logger.info("没找到数据");
        return "";
    }

    public void getInfoByIPAndDatabaseName(String IP,String databaseName){
        Connection conn = connect.connectMysql(IP,databaseName);
        logger.info("mysql 连接成功！");
    }

    public static void main(String[] args) {
        Route route = new Route();
        String str = route.getDatabaseByPhoneNumber(182222);
        String IP = str.split("\\/")[0];
        String databaseName = str.split("\\/")[1];
        System.out.println();

    }
}
