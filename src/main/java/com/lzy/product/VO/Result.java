package com.lzy.product.VO;

import lombok.Data;

/**
 * @author ：lzy
 * @date ：Created in 2019/2/27 10:49
 * @description：
 * @modified By：
 */
@Data
public class Result {

    private String code;

    private String message;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
