package com.joshua.craftsman.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.billboard.BillboardRankCraftsAdapter;
import com.joshua.craftsman.adapter.billboard.BillboardRankProAdapter;
import com.joshua.craftsman.entity.BillboardRankCrafts;
import com.joshua.craftsman.entity.BillboardRankPro;
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

public class BillBoardFragment extends BaseFragment  {


    @BindView(R.id.billboard_rank_pro_rv)
    RecyclerView billboardRankProRv;
    @BindView(R.id.billboard_rank_crafts_rv)
    RecyclerView billboardRankCraftsRv;

    private View view;
    private List<BillboardRankPro> list_rankPro;
    private List<BillboardRankCrafts> list_rankCrafts;

    @Override
    public View initView() {
        Log.e("TAG", "BillBoard-->initView()");
        view = View.inflate(mContext, R.layout.billboard, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list_rankPro = new ArrayList<>();
        list_rankCrafts = new ArrayList<>();
        initRankPro();
        getDataFromServer();
    }


    private void getDataFromServer() {
        getRankPro();
        getRankCrafts();
    }

    private void getRankPro() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.BILLBOARD_RANK_PRO)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseRankPro(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void getRankCrafts() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.BILLBOARD_RANK_CRAFTS)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseRankCrafts(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseRankPro(String result) {
        Gson gson = new Gson();
        list_rankPro = gson.fromJson(result, new TypeToken<List<BillboardRankPro>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRankPro();
            }
        });
    }

    private void parseRankCrafts(String result) {
        Gson gson = new Gson();
        list_rankPro = gson.fromJson(result, new TypeToken<List<BillboardRankCrafts>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRankCrafts();
            }
        });
    }

    private void initRankPro() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        billboardRankProRv.setLayoutManager(linearLayoutManager);
        billboardRankProRv.setAdapter(new BillboardRankProAdapter(getActivity(), list_rankPro));
    }

    private void initRankCrafts() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        billboardRankCraftsRv.setLayoutManager(linearLayoutManager);
        billboardRankCraftsRv.setAdapter(new BillboardRankCraftsAdapter(getActivity(), list_rankCrafts));
    }


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
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.billboard_hot:
                startActivity(new Intent(getActivity(), BillboardHotActivity.class));
                break;
            case R.id.billboard_more:
                startActivity(new Intent(getActivity(), BillboardMoreActivity.class));
                break;
            case R.id.billboard_pay:
                startActivity(new Intent(getActivity(), BillboardPayActivity.class));
                break;
            case R.id.billboard_craftsman:
                startActivity(new Intent(getActivity(), BillboardCraftsmanActivity.class));
                break;
        }
    }*/
}
