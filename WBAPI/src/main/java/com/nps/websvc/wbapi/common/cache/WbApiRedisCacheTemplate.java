/*
 * @(#)RedisCacheTemplate.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @desc wbapi redis 사용을 위한 cacaheTempalte
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Component
public class WbApiRedisCacheTemplate {

    @Autowired
    protected ShardedJedisPool redisPool;
    
    public void set(String key, Object value){
        int defaultExpire = 3600;
        set(key, value, defaultExpire);
    }

    public void set(String key, Object value, int expire) {
        ShardedJedis jedis = null;

        try {
            jedis = redisPool.getResource();
            jedis.set(key.getBytes(), SerializationUtils.serialize(value));
            jedis.expire(key, expire);
        } catch (JedisConnectionException e) {
            if (jedis != null)
                redisPool.returnBrokenResource(jedis);
        } finally {
            if (jedis != null)
                redisPool.returnResource(jedis);
        }
    }

    public Object get(String key) {
        ShardedJedis jedis = null;
        Object obj = null;

        try {
            byte[] value;
            jedis = redisPool.getResource();
            value = jedis.get(key.getBytes());
            obj = SerializationUtils.deserialize(value);
        } catch (JedisConnectionException e) {
            if (jedis != null)
                redisPool.returnBrokenResource(jedis);
        } finally {
            if (jedis != null)
                redisPool.returnResource(jedis);
        }

        return obj;
    }
    
    public boolean exists(String key){
        ShardedJedis jedis = redisPool.getResource();
        
        return jedis.exists(key);
    }

}
