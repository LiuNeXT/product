package com.lzy.product.service;

import com.alipay.api.AlipayApiException;
import com.lzy.product.bean.AlipayBean;
import com.lzy.product.utils.AliPayUtils;
import org.springframework.stereotype.Service;

/**
 * @author ：lzy
 * @date ：Created in 2019/3/14 14:15
 * @description：
 * @modified By：
 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements Payservice {
    @Override
    public String alipay(AlipayBean alipayBean) throws AlipayApiException {
        return AliPayUtils.connect(alipayBean);
    }
}
