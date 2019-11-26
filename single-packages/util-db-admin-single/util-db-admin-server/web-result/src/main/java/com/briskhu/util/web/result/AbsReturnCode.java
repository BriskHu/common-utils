package com.briskhu.util.web.result;

import lombok.Data;

/**
 * 基础响应码<p/>
 * 通过继承该类实现定制化的返回码。
 *
 * @author Brisk Hu
 * created on 2019-11-26
 **/
@Data
public class AbsReturnCode {
    String code;
    String msg;

    public AbsReturnCode(){
    }

    public AbsReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
