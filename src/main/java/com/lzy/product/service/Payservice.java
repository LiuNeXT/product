package com.lzy.product.service;

import com.alipay.api.AlipayApiException;
import com.lzy.product.bean.AlipayBean;

public interface Payservice {

    String alipay(AlipayBean alipayBean) throws AlipayApiException;
}
