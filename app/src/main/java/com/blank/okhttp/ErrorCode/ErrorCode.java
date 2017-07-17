package com.blank.okhttp.ErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blank on 2017/4/7.
 */

public class ErrorCode {
    //错误码
   public static final Map<Integer, String> errorMsg = new HashMap<Integer, String>() {
        {
            put(203901, "查询城市不能为空");
            put(203902, "查询不到该城市的天气");
            put(203903, "查询出错，请重试");
            put(203904, "错误的GPS坐标");
            put(203905, "GPS坐标解析出错，请确认提供的坐标正确（暂支持国内)");
            put(203906, "IP地址错误");
            put(203901, "查询不到该IP地址相关的天气信息");
            put(10001, "错误的请求KEY");
            put(10002, "该KEY无请求权限");
            put(10003, "KEY过期");
            put(10004, "错误的OPENID");
            put(10005, "应用未审核超时，请提交认证");
            put(10020, "接口维护");
            put(10021, "接口停用");

        }
    };
}
