package com.cbs.dataload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cbs.common.GetProperties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private String canal_destination = null;
    private static JedisPool pool = null;
    // 过期时间
    protected static int  expireTime = 60 * 60 *24;

    public static String redis_host = "127.0.0.1";
    public static String redis_port = "6379";
    public static String redis_user = "";
    public static String redis_pass = "";
    public static String redis_queuename = "canal_binlog_data"; //队列名称
    public static Map<String,String> redis_conf;
    private static String path = RedisUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    static String conf_path = path.substring(0, path.lastIndexOf("/")) + "/application.properties";

    public RedisUtil(String name){
        canal_destination = name;
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setBlockWhenExhausted(true);
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            config.setJmxEnabled(true);
            config.setMaxTotal(10);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(1000 * 100);
            config.setTestOnBorrow(true);
//            System.out.println(GetProperties.target.get(canal_destination).ip);
//            System.out.println(GetProperties.target.get(canal_destination).port);
            pool = new JedisPool(config, "127.0.0.1", 6379, 1000 * 10);
//            pool = new JedisPool(config, GetProperties.target.get(canal_destination).ip, GetProperties.target.get(canal_destination).port, 1000 * 10);
        }
     }


    public void push_redis(Map<String, String> conf, String[] argv) throws java.io.IOException {
        Jedis jedis = new Jedis("127.0.0.1");

        String host = conf.get("host");
        System.out.println(host);
        int port = Integer.parseInt(conf.get("port"));
        String user = conf.get("user");
        String pass = conf.get("pass");
        String queuename = conf.get("queuename");

//        Jedis jedis = new Jedis(host, port);
        for(int i=0;i<argv.length;i++){
            jedis.rpush(queuename, argv[i]);
        }

    }

    protected static synchronized Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            if (jedis != null) {
                jedis.close();
            }
        }
        return jedis;
    }

    /**
     * 释放jedis资源
     * @param jedis
     * @param isBroken
     */
    protected static void closeResource(Jedis jedis, boolean isBroken) {
        try {
            if (isBroken) {
                jedis.close();
            } else {
                jedis.close();
            }
        } catch (Exception e) {

        }
    }

    public static void delKey(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(0);
            jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
    }

    /**
     * 取得key的值
     * @param key
     */
    public static String stringGet(String key) {
        Jedis jedis = null;
        boolean isBroken = false;
        String lastVal = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            lastVal = jedis.get(key);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
        return lastVal;
    }

    /**
     * 添加string数据
     * @param key
     * @param value
     */
    public static String stringSet(String key, String value) {
        Jedis jedis = null;
        boolean isBroken = false;
        String lastVal = null;
        try {
            jedis = getJedis();
            jedis.select(0);
            lastVal = jedis.set(key, value);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            e.printStackTrace();
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
        return lastVal;
    }

    /**
     *  添加hash数据
     * @param key
     * @param field
     * @param value
     */
    public static void hashSet(String key, String field, String value) {
        boolean isBroken = false;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                jedis.select(0);
                jedis.hset(key, field, value);
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            isBroken = true;
        } finally {
            closeResource(jedis, isBroken);
        }
    }

    static void ConfigRedis(){

        try {
            Properties prop = new Properties();
            InputStream in = new FileInputStream(conf_path);

            prop.load(in);

            String conf_redis_host = prop.getProperty("spring,redis.host");
            String conf_redis_port = prop.getProperty("spring.redis.port");
            String conf_redis_user = prop.getProperty("redis.user");
            String conf_redis_pass = prop.getProperty("redis.pass");
            String conf_redis_queuename = prop.getProperty("redis.queuename");

            if (conf_redis_port!= null && conf_redis_port!=""){
                redis_host = conf_redis_host.trim();
            }
            if (conf_redis_port!= null && conf_redis_port!=""){
                redis_port = conf_redis_port.trim();
            }
            if (conf_redis_user!= null && conf_redis_user!=""){
                redis_user = conf_redis_user.trim();
            }
            if (conf_redis_pass!= null && conf_redis_pass!=""){
                redis_pass = conf_redis_pass.trim();
            }
            if (conf_redis_queuename!= null && conf_redis_queuename!=""){
                redis_queuename = conf_redis_queuename.trim();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        redis_conf = new HashMap<String, String>();
        redis_conf.put("host", redis_host);
        redis_conf.put("port", redis_port);
        redis_conf.put("user", redis_user);
        redis_conf.put("pass", redis_pass);
        redis_conf.put("queuename", redis_queuename);
    }
}
