package com.joshua.craftsman.wang.common.craft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.joshua.craftsman.R;
import com.joshua.craftsman.wang.common.p38;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 18012 on 2017/4/29.
 */

public class p16 extends AppCompatActivity {
    @BindView(R.id.pay)
    Button pay;//充值跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p16);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.pay)
    public void onViewClicked() {
        Intent intent=new Intent();
        intent.setClass(p16.this,p38.class);
        startActivity(intent);
    }
}
