package com.joshua.craftsman.fragment.craftHome;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.craftshome.CraftAlbumAdapter;
import com.joshua.craftsman.entity.Album;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.fragment.BaseFragment;
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

/**
 * Created by Lister on 2017/6/25.
 */

public class CraftAlbumFragment extends BaseFragment {

    @BindView(R.id.craft_album_rv)
    RecyclerView craftAlbumRv;
    //private List<Album> list_album;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.craft_album, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //list_album = new ArrayList<>();
        // getDataFromServer();
    }
/*
    private void getDataFromServer() {
        getBillboard();
    }
    private void getBillboard() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.ALBUM_LIST)
                .build();
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseBillboard(result);
            }
            @Override
            protected void error() {
            }
        });
    }
    private void parseBillboard(String result) {
        Gson gson = new Gson();
        list_album = gson.fromJson(result, new TypeToken<List<Album>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initLayout();
            }
        });
    }
    private void initLayout() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        craftAlbumRv.setLayoutManager(linearLayoutManager);
        craftAlbumRv.setAdapter(new CraftAlbumAdapter(getActivity(), list_album));
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}