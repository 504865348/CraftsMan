package com.joshua.craftsman.activity.find;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.HuoDongAdapter;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.entity.joshua.HuoDong;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FindActivityDetailActivity extends BaseActivity {

    @BindView(R.id.find_activity_tool_bar)
    Toolbar findActivityToolBar;
    @BindView(R.id.tv_content)
    TextView tv_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_content_activity_detail);
        ButterKnife.bind(this);
        findActivityToolBar.setTitle("");
        setSupportActionBar(findActivityToolBar);
        initData();


    }

    public void initData() {
        HuoDong huoDong = (HuoDong) getIntent().getSerializableExtra("huodong");
        tv_content.setText(huoDong.getactivityDetail());
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
