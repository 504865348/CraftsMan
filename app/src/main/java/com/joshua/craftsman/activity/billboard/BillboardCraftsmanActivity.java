package com.joshua.craftsman.activity.billboard;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.billboard.BillboardCraftsmanAdapter;
import com.joshua.craftsman.entity.BillboardCraftsman;
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

public class BillboardCraftsmanActivity extends BaseActivity {

    @BindView(R.id.billboard_craftsman_rv)
    RecyclerView billboard_craftsman_rv;

    private List<BillboardCraftsman> list_craftsman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billboard_craftsman);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        list_craftsman = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getCraftsman();
    }
    private void getCraftsman() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.BILLBOARD_CRAFTSMAN)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseCraftsman(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseCraftsman(String result) {
        Gson gson = new Gson();
        list_craftsman = gson.fromJson(result, new TypeToken<List<BillboardCraftsman>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleCraftsman();
            }
        });
    }

    private void initRecycleCraftsman() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        billboard_craftsman_rv.setLayoutManager(linearLayoutManager);
        billboard_craftsman_rv.setAdapter(new BillboardCraftsmanAdapter(this,list_craftsman));
    }
}

