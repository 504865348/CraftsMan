package com.joshua.craftsman.activity.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

public class MoneyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
    }

    public void pay(View view) {
        startActivity(new Intent(mBaseActivity,RechargeActivity.class));
    }
}
