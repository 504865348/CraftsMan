package com.joshua.craftsman.activity.classify;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassifyMiningActivity extends BaseActivity {

    @BindView(R.id.classify_mining_tool_bar)
    Toolbar classifyMiningToolBar;
    @BindView(R.id.classify_mining_rv)
    RecyclerView classifyMiningRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_mining);
        ButterKnife.bind(this);
        classifyMiningToolBar.setTitle("");
        setSupportActionBar(classifyMiningToolBar);
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
