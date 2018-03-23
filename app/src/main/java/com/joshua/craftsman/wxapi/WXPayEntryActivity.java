package com.joshua.craftsman.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.application.BaseApplication;
import com.joshua.craftsman.pay.event.MessageEvent;
import com.joshua.craftsman.pay.event.OrderNumber;
import com.joshua.craftsman.pay.util.PayUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    private String orderNumber="";
    private PayUtils payUtils=new PayUtils(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
//
        api = BaseApplication.api;
        api.handleIntent(getIntent(), this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(OrderNumber messageEvent) {
        orderNumber=messageEvent.getMessage();
    }


    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = resp.errCode;
            switch (code) {
                case 0:
                    Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new MessageEvent(orderNumber));
                    Map<String, String> result = new HashMap<>();
                    result.put("resultStatus", 9000 + "");
                    payUtils.postPayResultWx(result,orderNumber);
                    finish();
                    break;
                case -1:
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                    Map<String, String> result1 = new HashMap<>();
                    result1.put("resultStatus", 6001 + "");
                    payUtils.postPayResultWx(result1,orderNumber);
                    finish();
                    break;
                case -2:
                    Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
                    Map<String, String> result2 = new HashMap<>();
                    result2.put("resultStatus", 6001 + "");
                    payUtils.postPayResultWx(result2,orderNumber);
                    finish();
                    break;
                default:
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                    Map<String, String> result3 = new HashMap<>();
                    result3.put("resultStatus", 6001 + "");
                    payUtils.postPayResultWx(result3,orderNumber);
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }

    }
}