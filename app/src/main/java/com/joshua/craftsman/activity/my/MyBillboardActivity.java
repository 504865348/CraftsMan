package com.joshua.craftsman.activity.my;

import android.os.Bundle;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.ButterKnife;

public class MyBillboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_billboard);
        ButterKnife.bind(this);
    }

}
