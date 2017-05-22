package com.joshua.craftsman.wang.common.craft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joshua.craftsman.R;

import butterknife.ButterKnife;



public class count extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count);
        ButterKnife.bind(this);
    }
}
