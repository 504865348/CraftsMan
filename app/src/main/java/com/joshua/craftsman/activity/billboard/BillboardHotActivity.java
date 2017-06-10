package com.joshua.craftsman.activity.billboard;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.billboard.BillboardHotAdapter;
import com.joshua.craftsman.entity.BillboardHot;
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

public class BillboardHotActivity extends BaseActivity {

    @BindView(R.id.billboard_hot_program_rv)
    RecyclerView billboard_hot_program_rv;

    private List<BillboardHot> list_hot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard_hot_program);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        list_hot = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getHot();
    }
    private void getHot() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                //.add("method", Server.HOME_CRAFTSMAN)
                .add("method", Server.BILLBOARD_HOT)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseHot(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseHot(String result) {
        Gson gson = new Gson();
        list_hot = gson.fromJson(result, new TypeToken<List<BillboardHot>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleHot();
            }
        });
    }

    private void initRecycleHot() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        billboard_hot_program_rv.setLayoutManager(linearLayoutManager);
        billboard_hot_program_rv.setAdapter(new BillboardHotAdapter(this,list_hot));
    }
}

