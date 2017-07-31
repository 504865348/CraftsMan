package com.joshua.craftsman.activity.info;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubscribeActivity extends BaseActivity {

    @BindView(R.id.subscribe_toolbar)
    Toolbar subscribeToolbar;
    @BindView(R.id.subscribe_rv)
    RecyclerView subscribeRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_subscribe);
        ButterKnife.bind(this);
        subscribeToolbar.setTitle("");
        setSupportActionBar(subscribeToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
