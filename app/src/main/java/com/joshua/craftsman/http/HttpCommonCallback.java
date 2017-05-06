package com.joshua.craftsman.http;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * ============================================================
 * <p>
 * 版 权 ： 吴奇俊  (c) 2017
 * <p>
 * 作 者 : 吴奇俊
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2017/5/6 12:59
 * <p>
 * 描 述 ：
 * <p>
 * ============================================================
 **/

public abstract class HttpCommonCallback implements Callback {
    private static final String TAG="LOG";
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String responseJson=response.body().string();
        Log.d(TAG, "onResponse: "+responseJson);
        Gson gson=new Gson();
        ResponseInfo responseInfo=gson.fromJson(responseJson,ResponseInfo.class);
        if(!responseInfo.isAlive()){
            //// TODO: 2017/5/6
            Log.d(TAG, "onResponse: cookie过期");
            return;
        }
        if(responseInfo.isError()){
            Log.d(TAG, "onResponse: 出现异常");
            error();
        }else {
            String result=responseInfo.getResult();
            Log.d(TAG, "onResponse: "+result);
            success(result);
        }
    }

    protected abstract void success(String result);

    protected abstract void error();

    @Override
    public void onFailure(Call call, IOException e) {


    }

}
