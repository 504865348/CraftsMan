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
import com.joshua.craftsman.pay.event.PayResultEvent;
import com.joshua.craftsman.pay.util.PayUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    private PayUtils payUtils=new PayUtils(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = BaseApplication.api;
        api.handleIntent(getIntent(), this);

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


    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = resp.errCode;
            switch (code) {
                case 0:
                    Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new PayResultEvent("9000"));
                    finish();
                    break;
                case -1:
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new PayResultEvent("6001"));

                    finish();
                    break;
                case -2:
                    Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new PayResultEvent("6001"));
                    finish();
                    break;
                default:
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new PayResultEvent("6001"));
                    finish();
                    break;
            }
        }

    }
}