package com.briskhu.util.web.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;


/**
 * 通用Web响应结果<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-26
 **/
@Data
public class BasicResult<T> {

    /* ---------------------------------------- fileds ---------------------------------------- */
    private String code;
    private String msg;
    private T data;
    private boolean success;


    /* ---------------------------------------- methods ---------------------------------------- */
    public BasicResult() {
    }

    public BasicResult(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public BasicResult(AbsReturnCode absReturnCode) {
        this.code = absReturnCode.getCode();
        this.msg = absReturnCode.getMsg();
    }


    public static <T> BasicResult<T> ok() {
        BasicResult<T> result = new BasicResult<>(BasicReturnCode.SUCCESS);
        result.setSuccess(true);
        return result;
    }

    public static <T> BasicResult<T> ok(String message) {
        BasicResult<T> result = new BasicResult<>(BasicReturnCode.SUCCESS.getCode(), message);
        result.setSuccess(true);
        return result;
    }

    public static <T> BasicResult<T> ok(AbsReturnCode absReturnCode) {
        BasicResult<T> result = new BasicResult<>(absReturnCode);
        result.setSuccess(true);
        return result;
    }

    public static <T> BasicResult<T> fail() {
        BasicResult<T> result = new BasicResult<>(BasicReturnCode.FAIL);
        result.setSuccess(false);
        return result;
    }

    public static <T> BasicResult<T> fail(String message) {
        BasicResult<T> result = new BasicResult<>(BasicReturnCode.FAIL.getCode(), message);
        result.setSuccess(false);
        return result;
    }

    public static <T> BasicResult<T> fail(AbsReturnCode absReturnCode) {
        BasicResult<T> result = new BasicResult<>(absReturnCode);
        result.setSuccess(false);
        return result;
    }


    public BasicResult<T> addData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}


