package com.blank.okhttp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.blank.okhttp.api.API;
import com.blank.okhttp.modul.BaseResponse;
import com.blank.okhttp.modul.Temp;
import com.blank.okhttp.okhttp.OkHttpCallBack;
import com.blank.okhttp.okhttp.OkHttpClientManager;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.blank.okhttp", appContext.getPackageName());
    }
    @Test
    public void okhttp(){
        Map<String,String> param = new HashMap<String, String >();
        param.put("cityname","上海");
        param.put("dtype","json");
        param.put("format","2");
        param.put("key", API.APPKEY);
        System.out.print("code:");
        OkHttpClientManager.getInstance().requestGet(API.TEMP, param, new OkHttpCallBack<BaseResponse<Temp>>() {
            @Override
            public void onFailure(Call call, Exception e) {
                super.onFailure(call, e);
            }

            @Override
            public void onSuccess(BaseResponse<Temp> response) {
                System.out.print("run");
                Temp temp = response.result;
                Temp.Today today = response.result.today;
                int code = response.errorCode;
                System.out.print("code:"+code);
                Log.d(TAG, "onSuccess:  "+code);
//                Snackbar.make(view, today.temperature, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }
}
