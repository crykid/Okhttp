package com.blank.okhttp.okhttp;

import android.util.Log;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by blank on 2017/4/6.
 * OkHttp请求回调，用于处理请求结果
 */

public abstract class OkHttpCallBack<T> {
    public Type mType;
    private static final String TAG = "OkHttpCallBack";

    public OkHttpCallBack() {
        this.mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);

    }

    /**
     * 请求被取消的回调
     *
     * @param url
     */
    public void onCancel(String url) {
        Log.w(TAG, "onCancel: " + url);
    }

    public void onFailure(Call call, Exception e) {
        //TODO:可以弹窗

        Log.d(TAG, "onFailure: " + e.toString());
    }

    public void onSuccess(T response) {

    }

}
