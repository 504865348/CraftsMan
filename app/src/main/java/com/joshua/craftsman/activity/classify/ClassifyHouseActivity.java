package com.joshua.craftsman.activity.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.classify.ClassifyAdapter;
import com.joshua.craftsman.entity.Classify;
import com.joshua.craftsman.entity.Server;
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

public class ClassifyHouseActivity extends BaseActivity {

    @BindView(R.id.classify_house_tool_bar)
    Toolbar classifyHouseToolBar;
    @BindView(R.id.classify_house_rv)
    RecyclerView classifyHouseRv;

    private List<Classify> list_house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_houses);
        ButterKnife.bind(this);
        initData();
        classifyHouseToolBar.setTitle("");
        setSupportActionBar(classifyHouseToolBar);
    }

    public void initData() {
        list_house = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getHouse();
    }

    private void getHouse() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_CLASSIFY)
                .add("type", "houses")
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseHouse(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseHouse(String result) {
        Gson gson = new Gson();
        list_house = gson.fromJson(result, new TypeToken<List<Classify>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleHouse();
            }
        });
    }

    private void initRecycleHouse() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyHouseRv.setLayoutManager(linearLayoutManager);
        classifyHouseRv.setAdapter(new ClassifyAdapter(this, list_house));
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

