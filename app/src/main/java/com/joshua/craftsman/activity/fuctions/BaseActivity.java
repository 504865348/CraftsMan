package com.joshua.craftsman.activity.fuctions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;//

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder unbinder;
    public abstract int getContentViewId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initAllMembersView(savedInstanceState);
    }
    protected abstract void initAllMembersView(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        unbinder = ButterKnife.bind(this);
        unbinder.unbind();
        super.onDestroy();
    }
}

