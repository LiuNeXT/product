package com.lzy.product.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ：lzy
 * @date ：Created in 2019/3/14 13:34
 * @description：
 * @modified By：
 */
@Data
@Accessors(chain = true)
public class AlipayBean {

    private String out_trade_no;
    private String subject;
    private StringBuffer total_amount;
    private String body;
    private String timeout_express = "10m";
    private String product_code = "FAST_INSTANT_TRADE_PAY";

}
