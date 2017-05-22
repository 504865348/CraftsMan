package com.joshua.craftsman.wang.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joshua.craftsman.R;

import butterknife.ButterKnife;



public class common_change extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alter);
        ButterKnife.bind(this);
    }
}
