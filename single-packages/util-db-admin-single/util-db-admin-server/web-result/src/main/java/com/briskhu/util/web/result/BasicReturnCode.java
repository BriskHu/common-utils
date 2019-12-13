package com.briskhu.util.web.result;

import lombok.Data;

/**
 * 基本的返回码<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-26
 **/
@Data
public class BasicReturnCode extends AbsReturnCode {

    public static AbsReturnCode SUCCESS = new AbsReturnCode(0, "请求成功");
    public static AbsReturnCode FAIL = new AbsReturnCode(999999, "请求失败");


}