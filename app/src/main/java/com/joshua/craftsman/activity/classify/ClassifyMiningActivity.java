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

public class ClassifyMiningActivity extends BaseActivity {

    @BindView(R.id.classify_mining_tool_bar)
    Toolbar classifyMiningToolBar;
    @BindView(R.id.classify_mining_rv)
    RecyclerView classifyMiningRv;

    private List<Classify> list_mining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_mining);
        ButterKnife.bind(this);
        initData();
        classifyMiningToolBar.setTitle("");
        setSupportActionBar(classifyMiningToolBar);
    }

    public void initData() {
        list_mining = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getMining();
    }

    private void getMining() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_CLASSIFY)
                .add("type", "mining")
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseMining(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseMining(String result) {
        Gson gson = new Gson();
        list_mining = gson.fromJson(result, new TypeToken<List<Classify>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleMining();
            }
        });
    }

    private void initRecycleMining() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyMiningRv.setLayoutManager(linearLayoutManager);
        classifyMiningRv.setAdapter(new ClassifyAdapter(this, list_mining));
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
