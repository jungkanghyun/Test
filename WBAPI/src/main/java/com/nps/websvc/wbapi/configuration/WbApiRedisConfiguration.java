/*
 * @(#)RedisConfiguration.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @desc wbapi redis ¼³Á¤ºó
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */

@Configuration
public class WbApiRedisConfiguration {

    @Value("${wbapi.redis.host}")
    private String[] host;

    @Value("${wbapi.redis.port}")
    private String[] port;
    
    @Value("${webapi.redis.max-total}")
    private int maxTotal;
    
    @Value("${webapi.redis.max-idle}")
    private int maxIdle;
    
    @Value("${webapi.redis.max-wait}")
    private int maxWait;
    
    @Value("${webapi.redis.test-on-borrow}")
    private boolean testOnBorrow;

    @Bean
    List<JedisShardInfo> shards() {
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        
        JedisShardInfo si = null;
        for(int i = 0; i < host.length; i++){
            si = new JedisShardInfo(host[i], Integer.parseInt(port[i]));
            shards.add(si);
        }
        
        return shards;
    }

    @Bean
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(testOnBorrow);
        
        return poolConfig;
    }

    @Bean
    ShardedJedisPool shardedJedisPool() {
        ShardedJedisPool pool = new ShardedJedisPool(jedisPoolConfig(), shards());
        return pool;
    }

}
