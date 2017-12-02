package com.joshua.craftsman.activity.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.help_tool_bar)
    Toolbar helpToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_help);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        helpToolBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help_tool_bar:
                finish();
                break;
        }
    }
}
