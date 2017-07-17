package com.blank.okhttp.modul;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by blank on 2017/4/7.
 */

public class BaseResponse<T> implements Serializable {
    /**
     * resultcode : 200
     * reason : successed!
     * result : {"sk":{"temp":"17","wind_direction":"东风","wind_strength":"1级","humidity":"88%","time":"17:55"},"today":{"temperature":"15℃~21℃","weather":"中雨","weather_id":{"fa":"08","fb":"08"},"wind":"东北风微风","week":"星期四","city":"上海","date_y":"2017年04月06日","dressing_index":"较舒适","dressing_advice":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""},"future":[{"temperature":"15℃~21℃","weather":"中雨","weather_id":{"fa":"08","fb":"08"},"wind":"东北风微风","week":"星期四","date":"20170406"},{"temperature":"14℃~18℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"北风微风","week":"星期五","date":"20170407"},{"temperature":"14℃~23℃","weather":"多云转阵雨","weather_id":{"fa":"01","fb":"03"},"wind":"东风3-4 级","week":"星期六","date":"20170408"},{"temperature":"12℃~16℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"东北风微风","week":"星期日","date":"20170409"},{"temperature":"11℃~17℃","weather":"小雨转阵雨","weather_id":{"fa":"07","fb":"03"},"wind":"北风微风","week":"星期一","date":"20170410"},{"temperature":"14℃~23℃","weather":"多云转阵雨","weather_id":{"fa":"01","fb":"03"},"wind":"东风3-4 级","week":"星期二","date":"20170411"},{"temperature":"14℃~18℃","weather":"小雨转阴","weather_id":{"fa":"07","fb":"02"},"wind":"北风微风","week":"星期三","date":"20170412"}]}
     * error_code : 0
     */

    public String resultcode;
    public String reason;
    public T result;
    public int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public String getReason() {
        return reason;
    }

    public T getResult() {
        return result;
    }

    public int getErrorCode() {
        return error_code;
    }
}
