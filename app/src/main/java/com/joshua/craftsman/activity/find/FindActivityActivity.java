package com.joshua.craftsman.activity.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.activity.craftsHome.CraftsHomeActivity;
import com.joshua.craftsman.adapter.HuoDongAdapter;
import com.joshua.craftsman.adapter.find.FindFriendsAdapter;
import com.joshua.craftsman.entity.FindFriendsAttention;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.entity.joshua.HuoDong;
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

public class FindActivityActivity extends BaseActivity {

    @BindView(R.id.find_activity_tool_bar)
    Toolbar findActivityToolBar;
    @BindView(R.id.find_friends_recommend_rv)
    RecyclerView findFriendsRecommendRv;
    @BindView(R.id.empty_layout)
    FrameLayout empty_layout;
    private List<HuoDong> list_FF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_content_activity);
        ButterKnife.bind(this);
        findActivityToolBar.setTitle("");
        setSupportActionBar(findActivityToolBar);
        initData();


    }

    public void initData() {
        list_FF = new ArrayList<>();
        getDataFromServer();

    }
    private void getDataFromServer() {
        getFF();
    }

    private void getFF() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_ACTIVITY)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                Log.d("关注", "关注我的: "+result);
                parseFF(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseFF(String result) {
        Gson gson = new Gson();
        list_FF = gson.fromJson(result, new TypeToken<List<HuoDong>>() {
        }.getType());
        if (list_FF.get(0).getid().equals("null")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("关注", "关注我的: +empty");
                    setEmptyView(true);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(false);
                    initRecycleFF();
                }
            });
        }
    }

    private void initRecycleFF() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFriendsRecommendRv.setLayoutManager(linearLayoutManager);
        HuoDongAdapter adapter = new HuoDongAdapter(this,list_FF);
        findFriendsRecommendRv.setAdapter(adapter);
    }

    private void setEmptyView(Boolean isEmpty) {
        if(isEmpty){
            empty_layout.setVisibility(View.VISIBLE);
        }else {
            empty_layout.setVisibility(View.GONE);
        }
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
