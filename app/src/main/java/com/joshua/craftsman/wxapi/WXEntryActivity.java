package com.joshua.craftsman.wxapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joshua.craftsman.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
    }
}
