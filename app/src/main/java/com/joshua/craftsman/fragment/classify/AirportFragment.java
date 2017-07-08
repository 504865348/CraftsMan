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
 * 首页-分类-民航机场工程Fragment
 */

public class AirportFragment extends BaseFragment {

    @BindView(R.id.classify_airport_rv)
    RecyclerView classifyAirportRv;

    private List<Classify> list_airport;

    public static Fragment newInstance() {
        AirportFragment fragment = new AirportFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.classify_airport, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list_airport = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getAirport();
    }

    private void getAirport() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_CLASSIFY)
                .add("type", "airport")
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseAirport(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseAirport(String result) {
        Gson gson = new Gson();
        list_airport = gson.fromJson(result, new TypeToken<List<Classify>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleAirport();
            }
        });
    }

    private void initRecycleAirport() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyAirportRv.setLayoutManager(linearLayoutManager);
        classifyAirportRv.setAdapter(new ClassifyAdapter(getActivity(), list_airport));
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
