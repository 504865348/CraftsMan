package com.joshua.craftsman.activity.hot;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.HotSkillsAdapter;
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

public class SkillsActivity extends BaseActivity {

    @BindView(R.id.hot_skills_tool_bar)
    Toolbar hotSkillsToolBar;
    @BindView(R.id.hot_skills_rv)
    RecyclerView hotSkillsRv;

    private List<HotSkills> list_JXDY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_skills);
        ButterKnife.bind(this);
        initData();
        hotSkillsToolBar.setTitle("");
        setSupportActionBar(hotSkillsToolBar);
    }

    public void initData() {
        list_JXDY = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getJXDY();//匠心独运
    }

    private void getJXDY() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_SKILLS)
                .build();
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                parseJXDY(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseJXDY(String result) {
        Gson gson = new Gson();
        list_JXDY = gson.fromJson(result, new TypeToken<List<HotSkills>>() {
        }.getType());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleJXDY();
            }
        });
    }
    private void initRecycleJXDY() {
        //设置网格布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        hotSkillsRv.setLayoutManager(gridLayoutManager);
        hotSkillsRv.setAdapter(new HotSkillsAdapter(this, list_JXDY));
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
