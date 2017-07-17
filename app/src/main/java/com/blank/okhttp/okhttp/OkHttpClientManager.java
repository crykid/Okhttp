package com.blank.okhttp.okhttp;

import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.blank.okhttp.ErrorCode.ErrorCode;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.blank.okhttp.api.API.BASEURL;

/**
 * Created by blank on 2017/4/6.
 */

public class OkHttpClientManager {
    private static final String TAG = "OkHttpClientManager";
    private static OkHttpClientManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Gson mGson;
    private Handler mRequestHandler;


    private OkHttpClientManager() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }
        mOkHttpClient.newBuilder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS);
        mGson = new Gson();
    }

    public static OkHttpClientManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    public void requestGet(String url, Map params, OkHttpCallBack callback) {
        getInstance().request_GET(url, params, callback);
    }


    /**
     * GET 请求
     *
     * @param url
     * @param params
     * @param callback
     */
    private void request_GET(String url, Map params, OkHttpCallBack callback) {
        if (params != null && params.size() > 0) {
            url = getUrl(url, params);
        }
        Request request = new Request.Builder().url(url).build();
        deliveryResult(request, callback);
    }

    /**
     * Post 请求
     *
     * @param url
     * @param params   Type Map
     * @param callBack 请求结果回调接口
     */
    private void request_POST(String url, Map params, OkHttpCallBack callBack) {
        Param[] paramArr = MapToParam(params);
        Request request = buildPostRequest(url, paramArr);
        deliveryResult(request, callBack);
    }

    private Request buildPostRequest(String url, Param[] params) {
        if (params == null) {
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        FormBody formBody = builder.build();
        return new Request.Builder().url(url).post(formBody).header("Content-Type", "charset=utf-8").build();
    }

    /**
     * OkHttpClient
     *
     * @param request
     * @param callback
     */
    private void deliveryResult(final Request request, final OkHttpCallBack callback) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonResponse = response.body().string();
                Log.w(TAG, "onResponse ==> url: " + request.url() + "  jsonString : " + jsonResponse);
                Object jsonobj = mGson.fromJson(jsonResponse, callback.mType);
                int code = (int) getFieldValueByName("errorCode", jsonobj);

                checkErrorType(code);
                Log.w(TAG, "onResponse: error_code: " + code);
//                callback.onSuccess(jsonobj);


            }
        });
    }

    private void checkErrorType(int code) {
        for (int key : ErrorCode.errorMsg.keySet()) {
            if (key == code) {
                Log.w(TAG, "checkErrorType: errorType: " + ErrorCode.errorMsg.get(key));
                return;
            }
        }
    }

    /**
     * 将base url与传入的参数拼接后的url进行再拼接，（此处的baseurl为""）；
     * @param url
     * @param params
     * @return
     */
    private String getUrl(String url, Map<String, Object> params) {
        return BASEURL + url + "?" + getUrlFromParams(params);
    }


    /**
     * 把存入map的请求参数转化为param对象数组
     *
     * @param params Type Map;
     * @return Type Param[];
     */
    private Param[] MapToParam(Map<String, Object> params) {
        if (params == null) return new Param[0];
        //获取Map的size
        int size = params.size();
        Param[] paramArr = new Param[size];
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        int i = 0;
        //通过遍历将map中的参数添加到ParamArr数组中
        for (Map.Entry<String, Object> entry : entries) {
            paramArr[i++] = new Param(entry.getKey(), entry.getValue() + "");
        }
        return paramArr;
    }

    /**
     * 从Map中取出需要的参数并拼接后返回；
     *
     * @param paramMap Type Map<String,Object> ,包含参数的Map；
     * @return Type String ，返回拼接后的字符串；
     */
    private String getUrlFromParams(Map<String, Object> paramMap) {
        StringBuffer sb = null;
        if (paramMap != null) {
//            paramMap.putAll();//添加默认参数使用，例如位置信息，配置信息
            sb = new StringBuffer();
            int keyIndex = 0;
            for (Object key : paramMap.keySet()) {
                // if the current key were not the last one in the paramMap,append the "&"
                if (keyIndex < paramMap.size()) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(Uri.encode(paramMap.get(key) + ""));
            }
        }
        return sb.toString();
    }

    /**
     * 根据属性名获取属性值
     */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {

            //获取名称第一个字符
            String firstLetter = fieldName.substring(0, 1).toUpperCase();

            //拼接该属性的get方法
            String getter = "get" + firstLetter + fieldName.substring(1);

            //得到改对象的get方法
            Method method = o.getClass().getMethod(getter, new Class[]{});

            //调用该方法并得到返回值
            Object value = method.invoke(o, new Object[]{});

            return value;
        } catch (Exception e) {
            Log.w(TAG, "getFieldValueByName error : " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * 请求参数对象，post请求使用
     */
    public class Param {
        String key;
        String value;

        public Param(String key, String s) {
            this.key = key;
            this.value = s;
        }
    }


}
