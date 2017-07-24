package com.joshua.craftsman.activity.account;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProtocolActivity extends BaseActivity {

    @BindView(R.id.webView_protocol)
    WebView webViewProtocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);
        ButterKnife.bind(this);
        readHtmlFormAssets();
    }
    private void readHtmlFormAssets(){
        WebSettings webSettings = webViewProtocol.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webViewProtocol.setBackgroundColor(Color.TRANSPARENT);
        webViewProtocol.loadUrl("file:///android_asset/craftsmanProtocol.html");
    }
}
