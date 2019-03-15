package com.lzy.product.utils;

import com.alibaba.fastjson.JSON;
import com.alipay.api.*;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lzy.product.bean.AlipayBean;
import com.lzy.product.config.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author ：lzy
 * @date ：Created in 2019/3/14 13:58
 * @description：
 * @modified By：
 */
@Slf4j
public class AliPayUtils {

    public static String connect(AlipayBean alipayBean) throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(
                PropertiesConfig.getKey("gatewayUrl"),//支付宝网关
                PropertiesConfig.getKey("app_id"),//appid
                PropertiesConfig.getKey("merchant_private_key"),//商户私钥
                "json",
                PropertiesConfig.getKey("charset"),//字符编码格式
                PropertiesConfig.getKey("alipay_public_key"),//支付宝公钥
                PropertiesConfig.getKey("sign_type")//签名方式
        );

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(PropertiesConfig.getKey("return_url"));
        alipayRequest.setNotifyUrl(PropertiesConfig.getKey("notify_url"));
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

        String result = alipayClient.pageExecute(alipayRequest).getBody();
        alipayClient.pageExecute(alipayRequest);
        log.info("result={}",result);
        return result;

    }

}
