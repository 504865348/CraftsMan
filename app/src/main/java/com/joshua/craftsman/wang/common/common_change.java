package com.joshua.craftsman.wang.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joshua.craftsman.R;

import butterknife.ButterKnife;

/**
 * Created by 18012 on 2017/4/30.
 */

public class common_change extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alter);
        ButterKnife.bind(this);
    }
}
