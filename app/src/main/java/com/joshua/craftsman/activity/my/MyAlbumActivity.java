package com.joshua.craftsman.activity.my;

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
import com.joshua.craftsman.adapter.craftmy.MyAlbumAdapter;
import com.joshua.craftsman.entity.Album;
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

public class MyAlbumActivity extends BaseActivity {

    @BindView(R.id.myAlbum_toolbar)
    Toolbar myAlbumToolbar;
    @BindView(R.id.myAlbum_rv)
    RecyclerView myAlbumRv;

    private OkHttpClient mClient;
    private List<Album> list_album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_album);
        ButterKnife.bind(this);
        myAlbumToolbar.setTitle("");
        setSupportActionBar(myAlbumToolbar);
        initData();
    }

    public void initData() {
        list_album = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {

        mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(this))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.ALBUM_LIST)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                Log.d(TAG, "success: " + result);
                parseData(result);
            }

            @Override
            protected void error() {

            }
        });

    }

    private void parseData(String result) {
        Gson gson = new Gson();
        list_album = gson.fromJson(result, new TypeToken<List<Album>>() {
        }.getType());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycle();
            }
        });
    }

    private void initRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAlbumRv.setLayoutManager(linearLayoutManager);
        myAlbumRv.setAdapter(new MyAlbumAdapter(this, list_album));
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
