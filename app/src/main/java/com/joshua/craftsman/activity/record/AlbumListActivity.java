package com.joshua.craftsman.activity.record;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.HotCraftsAdapter;
import com.joshua.craftsman.entity.Album;
import com.joshua.craftsman.entity.HotCraftsman;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.joshua.craftsman.R.id.hot_crafts_rv;
import static okhttp3.Protocol.get;

public class AlbumListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.select_album_create)
    LinearLayout select_album_create;
    @BindView(R.id.album_list_lv)
    RecyclerView album_list_lv;

    private OkHttpClient mClient;
    private List<Album> list_album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_album_window);
        ButterKnife.bind(this);
        select_album_create.setOnClickListener(this);
        getDataFromServer();

    }

    //获取专辑列表
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
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        album_list_lv.setLayoutManager(linearLayoutManager);
        AlbumAdapter albumAdapter = new AlbumAdapter(this, list_album);
        albumAdapter.setOnRecyclerViewItemClickListener(new AlbumAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Album album = list_album.get(position);
                Intent intent=new Intent();
                intent.putExtra("albumTitle",album.getTitle());
                intent.putExtra("albumId",album.getAlbumID());
                Log.d(TAG, "onItemClick: "+album.getAlbumID());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        album_list_lv.setAdapter(albumAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_album_create:
                Intent intent = new Intent(this, CreateAlbumActivity.class);
                startActivity(intent);
                break;
        }
    }
}