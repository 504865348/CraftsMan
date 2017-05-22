package com.joshua.craftsman.wang.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.joshua.craftsman.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class common_money extends AppCompatActivity {
    @BindView(R.id.pay)
    Button pay;//充值跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.pay)
    public void onViewClicked() {
        Intent intent=new Intent();
        intent.setClass(common_money.this,common_recharge.class);
        startActivity(intent);
    }
}
