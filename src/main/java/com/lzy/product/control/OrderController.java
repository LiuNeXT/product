package com.lzy.product.control;

import com.alipay.api.AlipayApiException;
import com.lzy.product.bean.AlipayBean;
import com.lzy.product.service.Payservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lzy
 * @date ：Created in 2019/3/14 14:17
 * @description：
 * @modified By：
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private Payservice payservice;

    @PostMapping("/alipay")
    public String alipay(String out_trade_no, String subject, String total_amount, String body) throws AlipayApiException {
        return payservice.alipay(new AlipayBean()
                .setBody(body).setOut_trade_no(out_trade_no)
                .setTotal_amount(new StringBuffer().append(total_amount))
                .setSubject(subject));
    }


}
