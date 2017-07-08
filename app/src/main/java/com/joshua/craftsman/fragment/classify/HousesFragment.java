package com.joshua.craftsman.fragment.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.classify.ClassifyAdapter;
import com.joshua.craftsman.entity.Classify;
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
 * Created by nzz on 2017/6/24.
 * 首页-分类-房屋建筑Fragment
 */

public class HousesFragment extends BaseFragment {

    @BindView(R.id.classify_house_rv)
    RecyclerView classifyHouseRv;

    private List<Classify> list_house;

    public static Fragment newInstance() {
        HousesFragment fragment = new HousesFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.classify_houses, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list_house = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getHouse();
    }

    private void getHouse() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_CLASSIFY)
                .add("type", "houses")
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseHouse(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseHouse(String result) {
        Gson gson = new Gson();
        list_house = gson.fromJson(result, new TypeToken<List<Classify>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleHouse();
            }
        });
    }

    private void initRecycleHouse() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyHouseRv.setLayoutManager(linearLayoutManager);
        classifyHouseRv.setAdapter(new ClassifyAdapter(getActivity(), list_house));
    }

    @Nullable
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
