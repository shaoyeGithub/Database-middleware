package com.cbs.common;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * RedisApi
 *
 * @author
 * @version 1.0.0
 */

// 用于连接redis
public class RedisApi {
    private String canal_destination = null;
    private static JedisPool pool = null;

    public RedisApi(String name) {
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
            pool = new JedisPool(config, GetProperties.target.get(canal_destination).ip, GetProperties.target.get(canal_destination).port, 1000 * 10);
        }
    }

    /**
     * return Resource to pool
     *
     * @param pool
     * @param redis
     */
    public void returnResource(Jedis redis) {
        if (redis != null) {
            redis.close();
        }
    }

    /**
     * get data
     *
     * @param key
     * @return
     */
    public String get(String key) throws Exception {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
        return value;
    }

    /**
     * zrange data
     *
     * @param key
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Set zrange(String key) throws Exception {
        Set value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.zrange(key, 0, -1);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
        return value;
    }

    /**
     * lrange data
     *
     * @param key
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List lrange(String key) throws Exception {
        List value = null;
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            value = jedis.lrange(key, 0, -1);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
        return value;
    }

    /**
     * set string
     *
     * @param key
     * @return
     * @throws Exception
     */
    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, value);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * set data
     *
     * @param key
     * @return
     */
    public void zadd(String key, String member) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            long score = (jedis.exists(key)) ? (jedis.zcard(key)) : 0;
            jedis.zadd(key, score, member);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * push list in left
     *
     * @param key
     * @return
     */
    public void lpush(String key, String member) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.lpush(key, member);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * push list in right
     *
     * @param key
     * @return
     */
    public void rpush(String key, String member) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.rpush(key, member);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * exists
     *
     * @param key
     * @return
     */
    public boolean exists(String key) throws Exception {

        Jedis jedis = null;
        boolean blag = false;
        try {
            jedis = pool.getResource();
            blag = jedis.exists(key);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
        return blag;
    }

    /**
     * del
     *
     * @param key
     * @return
     */
    public void del(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(key);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * lrem
     *
     * @param key
     * @return
     */
    public void lrem(String key, String member) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.lrem(key, 1, member);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * zrem
     *
     * @param key
     * @return
     */
    public void zrem(String key, String member) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.zrem(key, member);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * expire
     *
     * @param key
     * @param num
     */
    public void expire(String key, int num) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.expire(key, num * 60);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * incr 1
     *
     * @param key
     */
    public void incr(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.incr(key);
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }

    /**
     * clear
     *
     * @param num
     */
    public void clear() throws Exception {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.flushDB();
        } catch (JedisConnectionException e) {
            throw new Exception(" redis link fail", e);
        }
        returnResource(jedis);
    }
}
