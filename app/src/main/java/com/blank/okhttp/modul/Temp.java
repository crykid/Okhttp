package com.blank.okhttp.modul;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by blank on 2017/4/6.
 */

public class Temp {


    /**
     * sk : {"temp":"14","wind_direction":"东风","wind_strength":"1级","humidity":"94%","time":"10:12"}
     * today : {"temperature":"13℃~18℃","weather":"阵雨转阴","weather_id":{"fa":"03","fb":"02"},"wind":"北风微风","week":"星期五","city":"上海","date_y":"2017年04月07日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
     * future : [{"temperature":"13℃~18℃","weather":"阵雨转阴","weather_id":{"fa":"03","fb":"02"},"wind":"北风微风","week":"星期五","date":"20170407"},{"temperature":"12℃~23℃","weather":"多云转阵雨","weather_id":{"fa":"01","fb":"03"},"wind":"东风微风","week":"星期六","date":"20170408"},{"temperature":"12℃~16℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"东北风3-4 级","week":"星期日","date":"20170409"},{"temperature":"12℃~17℃","weather":"阵雨转中雨","weather_id":{"fa":"03","fb":"08"},"wind":"东北风微风","week":"星期一","date":"20170410"},{"temperature":"12℃~19℃","weather":"多云","weather_id":{"fa":"01","fb":"01"},"wind":"东北风微风","week":"星期二","date":"20170411"},{"temperature":"12℃~16℃","weather":"中雨转小雨","weather_id":{"fa":"08","fb":"07"},"wind":"东北风3-4 级","week":"星期三","date":"20170412"},{"temperature":"12℃~17℃","weather":"阵雨转中雨","weather_id":{"fa":"03","fb":"08"},"wind":"东北风微风","week":"星期四","date":"20170413"}]
     */

    @SerializedName("sk")
    public Sk sk;
    @SerializedName("today")
    public Today today;
    @SerializedName("future")
    public List<Future> future;

    public static class Sk {
        /**
         * temp : 14
         * wind_direction : 东风
         * wind_strength : 1级
         * humidity : 94%
         * time : 10:12
         */

        @SerializedName("temp")
        public String temp;
        @SerializedName("wind_direction")
        public String windDirection;
        @SerializedName("wind_strength")
        public String windStrength;
        @SerializedName("humidity")
        public String humidity;
        @SerializedName("time")
        public String time;
    }

    public static class Today {
        /**
         * temperature : 13℃~18℃
         * weather : 阵雨转阴
         * weather_id : {"fa":"03","fb":"02"}
         * wind : 北风微风
         * week : 星期五
         * city : 上海
         * date_y : 2017年04月07日
         * dressing_index : 较冷
         * dressing_advice : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
         * uv_index : 最弱
         * comfort_index :
         * wash_index : 不宜
         * travel_index : 较不宜
         * exercise_index : 较不宜
         * drying_index :
         */

        @SerializedName("temperature")
        public String temperature;
        @SerializedName("weather")
        public String weather;
        @SerializedName("weather_id")
        public WeatherId weatherId;
        @SerializedName("wind")
        public String wind;
        @SerializedName("week")
        public String week;
        @SerializedName("city")
        public String city;
        @SerializedName("date_y")
        public String dateY;
        @SerializedName("dressing_index")
        public String dressingIndex;
        @SerializedName("dressing_advice")
        public String dressingAdvice;
        @SerializedName("uv_index")
        public String uvIndex;
        @SerializedName("comfort_index")
        public String comfortIndex;
        @SerializedName("wash_index")
        public String washIndex;
        @SerializedName("travel_index")
        public String travelIndex;
        @SerializedName("exercise_index")
        public String exerciseIndex;
        @SerializedName("drying_index")
        public String dryingIndex;

        public static class WeatherId {
            /**
             * fa : 03
             * fb : 02
             */

            @SerializedName("fa")
            public String fa;
            @SerializedName("fb")
            public String fb;
        }
    }

    public static class Future {
        /**
         * temperature : 13℃~18℃
         * weather : 阵雨转阴
         * weather_id : {"fa":"03","fb":"02"}
         * wind : 北风微风
         * week : 星期五
         * date : 20170407
         */

        @SerializedName("temperature")
        public String temperature;
        @SerializedName("weather")
        public String weather;
        @SerializedName("weather_id")
        public Today.WeatherId weatherId;
        @SerializedName("wind")
        public String wind;
        @SerializedName("week")
        public String week;
        @SerializedName("date")
        public String date;
    }
}
