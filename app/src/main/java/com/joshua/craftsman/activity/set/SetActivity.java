package com.joshua.craftsman.activity.set;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.account.LoginActivity;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.set_tool_bar)
    Toolbar setToolBar;
    @BindView(R.id.set_ll_bind)
    LinearLayout setLlBind;
    @BindView(R.id.set_ll_change_pwd)
    LinearLayout setLlChangePwd;
    @BindView(R.id.set_ll_continue)
    LinearLayout setLlContinue;
    @BindView(R.id.set_ll_mobile)
    LinearLayout setLlMobile;
    @BindView(R.id.set_ll_recommend)
    LinearLayout setLlRecommend;
    @BindView(R.id.set_ll_help)
    LinearLayout setLlHelp;
    @BindView(R.id.set_ll_about)
    LinearLayout setLlAbout;
    @BindView(R.id.set_btn_exit)
    Button setBtnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        ButterKnife.bind(this);
        initListener();
        setToolBar.setTitle("");
        setSupportActionBar(setToolBar);
    }

    private void initListener() {
        setLlBind.setOnClickListener(this);
        setLlChangePwd.setOnClickListener(this);
        setLlContinue.setOnClickListener(this);
        setLlMobile.setOnClickListener(this);
        setLlRecommend.setOnClickListener(this);
        setLlHelp.setOnClickListener(this);
        setLlAbout.setOnClickListener(this);
        setBtnExit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_ll_bind:
                startActivity(new Intent(mBaseActivity, BindActivity.class));
                break;
            case R.id.set_ll_change_pwd:
                startActivity(new Intent(mBaseActivity, EditPswActivity.class));
                break;
            case R.id.set_ll_continue:
                break;
            case R.id.set_ll_mobile:
                break;
            case R.id.set_ll_recommend:
                break;
            case R.id.set_ll_help:
                startActivity(new Intent(mBaseActivity, HelpActivity.class));
                break;
            case R.id.set_ll_about:
                break;
            case R.id.set_btn_exit:
                startActivity(new Intent(mBaseActivity, LoginActivity.class));
                break;
        }
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
