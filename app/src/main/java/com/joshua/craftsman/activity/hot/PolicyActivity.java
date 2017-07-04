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
import com.joshua.craftsman.adapter.HotPolicyAdapter;
import com.joshua.craftsman.entity.HotPolicy;
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

public class PolicyActivity extends BaseActivity {

    @BindView(R.id.hot_policy_tool_bar)
    Toolbar hotPolicyToolBar;
    @BindView(R.id.hot_policy_rv)
    RecyclerView hotPolicyRv;

    private List<HotPolicy> list_JZC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_policy);
        ButterKnife.bind(this);
        initData();
        hotPolicyToolBar.setTitle("");
        setSupportActionBar(hotPolicyToolBar);
    }

    public void initData() {
        list_JZC = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getJZC();//讲政策
    }

    private void getJZC() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_POLICY)
                .build();
        Log.d(TAG, "getJZC: " + Server.HOME_HOT_POLICY);

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseJZC(result);
            }

            @Override
            protected void error() {

            }
        });
    }


    private void parseJZC(String result) {
        Gson gson = new Gson();
        list_JZC = gson.fromJson(result, new TypeToken<List<HotPolicy>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleJZC();
            }
        });
    }

    private void initRecycleJZC() {
        //设置网格布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        hotPolicyRv.setLayoutManager(gridLayoutManager);
        hotPolicyRv.setAdapter(new HotPolicyAdapter(this, list_JZC));
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
