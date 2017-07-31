package com.joshua.craftsman.activity.hot;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.HotMoreCraftAdapter;
import com.joshua.craftsman.entity.HotCraftsman;
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

public class CraftsActivity extends BaseActivity {

    @BindView(R.id.hot_crafts_tool_bar)
    Toolbar hotCraftsToolBar;
    @BindView(R.id.hot_crafts_rv)
    RecyclerView hotCraftsRv;

    private List<HotCraftsman> list_DGGJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_crafts);
        ButterKnife.bind(this);
        initData();
        hotCraftsToolBar.setTitle("");
        setSupportActionBar(hotCraftsToolBar);
    }

    public void initData() {
        list_DGGJ = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getGJ();//工匠
    }

    private void getGJ() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_CRAFTSMAN)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseGJ(result);
            }

            @Override
            protected void error() {

            }
        });
    }


    private void parseGJ(String result) {
        Gson gson = new Gson();
        list_DGGJ = gson.fromJson(result, new TypeToken<List<HotCraftsman>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleGJ();
            }
        });
    }

    private void initRecycleGJ() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hotCraftsRv.setLayoutManager(linearLayoutManager);
        hotCraftsRv.setAdapter(new HotMoreCraftAdapter(this,list_DGGJ));
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
