package com.lzy.product.repository;

import com.lzy.product.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest  {


    private static final Logger logger = LoggerFactory.getLogger(ProductInfoRepositoryTest.class);

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public  void  findByAll(){
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        log.info(productInfoList.toString());
        Assert.assertNotNull(productInfoList);
        String s = "abc";
        log.info("log={}",s);
    }

    @Test
    public  void  test(){
        redisTemplate.boundValueOps("productInfo")
                .set(productInfoRepository.findAll());
    }

}