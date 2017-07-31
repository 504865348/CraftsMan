package com.joshua.craftsman.activity.info;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity {

    @BindView(R.id.collect_toolbar)
    Toolbar collectToolbar;
    @BindView(R.id.collect_rv)
    RecyclerView collectRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_collect);
        ButterKnife.bind(this);
        collectToolbar.setTitle("");
        setSupportActionBar(collectToolbar);
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
