package com.joshua.craftsman.activity.hot;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.HotMoreAlbumAdapter;
import com.joshua.craftsman.entity.HotSkills;
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

public class LookActivity extends BaseActivity {

    @BindView(R.id.hot_look_tool_bar)
    Toolbar hotLookToolBar;
    @BindView(R.id.hot_look_rv)
    RecyclerView hotLookRv;

    private List<HotSkills> list_KLQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_look);
        ButterKnife.bind(this);
        initData();
        hotLookToolBar.setTitle("");
        setSupportActionBar(hotLookToolBar);
    }

    public void initData() {
        list_KLQ = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getKLQ();//看利器
    }

    private void getKLQ() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_LOOK)
                .build();
        Log.d(TAG, "getKLQ: " + Server.HOME_HOT_LOOK);
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {

                parseKLQ(result);
            }

            @Override
            protected void error() {
            }
        });
    }


    private void parseKLQ(String result) {
        Gson gson = new Gson();
        list_KLQ = gson.fromJson(result, new TypeToken<List<HotSkills>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleKLQ();
            }
        });
    }

    private void initRecycleKLQ() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hotLookRv.setLayoutManager(linearLayoutManager);
        hotLookRv.setAdapter(new HotMoreAlbumAdapter(this, list_KLQ));
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
