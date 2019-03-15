package com.lzy.product.dao;

import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.logging.Logger;

/**
 * @author ：lzy
 * @date ：Created in 2019/3/8 10:01
 * @description：
 * @modified By：
 */
public class RedisDao {

    private  final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private JedisPool jedisPool;

    public RedisDao(String ip,String port){
        jedisPool = new JedisPool(ip, Integer.parseInt(port));
        try {
            
        } finally {

        }
    }


}
