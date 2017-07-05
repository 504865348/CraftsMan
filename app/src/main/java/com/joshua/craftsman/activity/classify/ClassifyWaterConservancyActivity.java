package com.joshua.craftsman.activity.classify;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassifyWaterConservancyActivity extends BaseActivity {

    @BindView(R.id.classify_waterConservancy_tool_bar)
    Toolbar classifyWaterConservancyToolBar;
    @BindView(R.id.classify_waterConservancy_rv)
    RecyclerView classifyWaterConservancyRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_waterconservancy);
        ButterKnife.bind(this);
        classifyWaterConservancyToolBar.setTitle("");
        setSupportActionBar(classifyWaterConservancyToolBar);
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
