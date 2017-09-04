package com.joshua.craftsman.pay.util;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.application.BaseApplication;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.entity.joshua.OrderType;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.utils.PrefUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.joshua.craftsman.R.id.money;
import static com.joshua.craftsman.entity.Server.SERVER_SEND_ORDER;

/**
 * class description here
 *
 * @author wqj
 * @version 1.0.0
 * @since 2017-03-29
 */
public class PayUtils {
    private String TAG = "PayUtils";
    public final String APPID = "2017062307555030";
//    public static final String RSA2_PRIVATE = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj4shSKLjJwHQY6FO7skXIIAh9b3jEcEAROTuxKZ9r3/6poZnMLIsECUbQhHErtUWAu2aplzGtGPXhDXT6zE2T35LZxmUNLfq27uin1ZqT0HqUgrVh9oQdAz0lB16nSX9U3h5/Cfh8GLnJKDPuKcDwOwQPlbvqI8VHsvyX4+Fj84eKuu057qDvo8ye6NHiJjV8Y4TnaqMnI9HEiOtLxiG25OG+/RvAkhZ89ahafCn8M+1teFkpVbFIBBnQeMP+lg1CeqL+tfSYZET6KdTtuU4O//mVPL3M9n2bqi3F3rMhtUU5ejmf2Z3dILG1QIXQUZNXkGUTjAk/KmhXs7eNN7FCQIDAQAB";

    private PaySuccess paySuccess;
    public static PayUtils payUtils = new PayUtils();
    private OkHttpClient mClient;

    public PayUtils() {
        mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(BaseApplication.getApplication()))
                .build();
    }

    public void setPaySuccess(PaySuccess paySuccess) {
        this.paySuccess = paySuccess;
    }

    public static PayUtils getPayUtils() {
        return payUtils;
    }

    /**
     * 支付宝支付业务
     */
    public void payV2(final Activity activity,String type,String id, float money) {

        Log.i(TAG, "payV2: ");
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, money, true);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        Log.i("msp", ">>>>>>>>>>>>>>>>>>>>>>>>>>orderParam:" + params.toString());
        /**
         * 访问服务器
         * 上传订单信息
         */

        //这里有个method的问题需要解决
        //两个都传了
        //在结尾加上&key并返回
//        String sign = OrderInfoUtil2_0.getSign(params, RSA2_PRIVATE, true);
//        final String orderInfo = orderParam + "&" + sign;
        RequestBody payParams = new FormBody.Builder()
                .add("method",Server.SERVER_SEND_ORDER)
                .add("type", type)
                .add("id",id)
                .add("count", money+"")
//                .add("orderParam",orderParam)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(payParams)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseJson = response.body().string();
                Log.d(TAG, "onResponse: " + responseJson);
                try {
                    JSONObject jo = new JSONObject(responseJson);
                    final String orderInfo = jo.getString("result");
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(activity);
                            Map<String, String> result = alipay.payV2(orderInfo, true);
                            Log.d("msp", "<<<<<<<<<<<<payResult>>>>>>>>>>: " + result.toString());
                            postPayResult(result);
                        }
                    };

                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 将支付结果发送给服务器
     */
    private void postPayResult(Map<String, String> result) {
        if (result.get("resultStatus").equals("6001")) {
            Toast.makeText(BaseApplication.getApplication(), "操作已经取消", Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "postPayResult: ");

        RequestBody resultParams = new FormBody.Builder()
                .add("resultStatus", result.get("resultStatus"))
                .add("result", result.get("result"))
                .add("memo", result.get("memo"))
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(resultParams)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                if (TextUtils.equals(result, "9000")) {
                    paySuccess.onSccess();
                } else {
                    Toast.makeText(BaseApplication.getApplication(), "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
