package com.joshua.craftsman.activity.hot;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.HotListenAdapter;
import com.joshua.craftsman.entity.HotListen;
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

public class ListenActivity extends BaseActivity {

    @BindView(R.id.hot_listen_tool_bar)
    Toolbar hotListenToolBar;
    @BindView(R.id.hot_listen_rv)
    RecyclerView hotListenRv;

    private List<HotListen> list_TZT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_listen);
        ButterKnife.bind(this);
        initData();
        hotListenToolBar.setTitle("");
        setSupportActionBar(hotListenToolBar);
    }

    public void initData() {
        list_TZT = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getTZT();//听专题
    }

    private void getTZT() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_LISTEN)
                .build();
        Log.d(TAG, "getTZT: " + Server.HOME_HOT_LISTEN);
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseTZT(result);
            }

            @Override
            protected void error() {

            }
        });
    }


    private void parseTZT(String result) {
        Gson gson = new Gson();
        list_TZT = gson.fromJson(result, new TypeToken<List<HotListen>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleTZT();
            }
        });
    }

    private void initRecycleTZT() {
        //设置网格布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        hotListenRv.setLayoutManager(gridLayoutManager);
        hotListenRv.setAdapter(new HotListenAdapter(this, list_TZT));
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
