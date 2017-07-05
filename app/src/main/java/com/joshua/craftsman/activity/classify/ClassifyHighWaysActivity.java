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

public class ClassifyHighWaysActivity extends BaseActivity {

    @BindView(R.id.classify_highWays_tool_bar)
    Toolbar classifyHighWaysToolBar;
    @BindView(R.id.classify_highWays_rv)
    RecyclerView classifyHighWaysRv;

    private List<Classify> list_highWays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_highways);
        ButterKnife.bind(this);
        initData();
        classifyHighWaysToolBar.setTitle("");
        setSupportActionBar(classifyHighWaysToolBar);
    }

    public void initData() {
        list_highWays = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getHighWays();
    }

    private void getHighWays() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_CLASSIFY)
                .add("type", "highWays")
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseHighWays(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseHighWays(String result) {
        Gson gson = new Gson();
        list_highWays = gson.fromJson(result, new TypeToken<List<Classify>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleHighWays();
            }
        });
    }

    private void initRecycleHighWays() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyHighWaysRv.setLayoutManager(linearLayoutManager);
        classifyHighWaysRv.setAdapter(new ClassifyAdapter(this, list_highWays));
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

