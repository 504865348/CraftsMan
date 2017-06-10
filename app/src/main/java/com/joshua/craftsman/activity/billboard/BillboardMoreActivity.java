package com.joshua.craftsman.activity.billboard;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.billboard.BillboardMoreAdapter;
import com.joshua.craftsman.entity.BillboardMore;
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

public class BillboardMoreActivity extends BaseActivity {

    @BindView(R.id.billboard_more_program_rv)
    RecyclerView billboard_more_program_rv;

    private List<BillboardMore> list_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard_more_program);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        list_more = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getMore();
    }
    private void getMore() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.BILLBOARD_MORE)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseMore(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseMore(String result) {
        Gson gson = new Gson();
        list_more = gson.fromJson(result, new TypeToken<List<BillboardMore>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleMore();
            }
        });
    }

    private void initRecycleMore() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        billboard_more_program_rv.setLayoutManager(linearLayoutManager);
        billboard_more_program_rv.setAdapter(new BillboardMoreAdapter(this,list_more));
    }
}

