package com.lzy.product.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lzy.product.VO.Result;
import com.lzy.product.dataobject.ProductInfo;
import com.lzy.product.repository.ProductInfoRepository;
import com.lzy.product.utils.Cookeriseerer;
import com.lzy.product.utils.CookiesUtils;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：lzy
 * @date ：Created in 2019/2/26 17:58
 * @description：
 * @modified By：
 */
@RestController
@Slf4j
public class ProductInfoControl {

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;


    private static HashMap<Integer, Object> map = new HashMap<>();

    static {
        map.put(1,"语文");
        map.put(2,"数学");
    }




    @RequestMapping("/getproductinfo")
    public List<ProductInfo> findByAll() {
        log.info(String.valueOf(new Date()));
        List<ProductInfo> productInfoList = productInfoRepository.findAll();
        String.format(CookiesUtils.ID,"123");



        return productInfoList;

    }


    @RequestMapping("/getvalue")
    public String process() {
        String result = stringRedisTemplate.opsForValue().get("productlist");
        if (result == null) {
            List<ProductInfo> productlist = productInfoRepository.findAll();
            //序列化
            result = JSON.toJSONString(productlist);
            //反序列化
            stringRedisTemplate.opsForValue().set("asdas", result);
            return result;
        }
        return result;
    }


    @RequestMapping("/sendMessage")
    public  void  send(){
        String message = JSON.toJSONString(productInfoRepository.findAll());
        amqpTemplate.convertAndSend("productInfo",message);
    }

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){

        List<ProductInfo> productInfoList = JSONArray.parseArray(message,ProductInfo.class);
        for (ProductInfo productInfo : productInfoList) {
            System.out.println(productInfo.toString());
        }
        //log.info("从队列【{}】接收到消息：{}", "productInfo"+ productInfoList);

    }
}


