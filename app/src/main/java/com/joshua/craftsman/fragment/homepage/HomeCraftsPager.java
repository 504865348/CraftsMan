package com.joshua.craftsman.fragment.homepage;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.HomeCraftsAdapter;
import com.joshua.craftsman.adapter.HotCraftsAdapter;
import com.joshua.craftsman.entity.Craftsman;
import com.joshua.craftsman.entity.HotCraftsman;
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

public class HomeCraftsPager extends BaseFragment {

    @BindView(R.id.home_page_crafts_gv)
    RecyclerView home_page_crafts_gv;

    private List<HotCraftsman> list_GJ;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.home_page_crafts, null);
        return view;
    }


    @Override
    public void initData() {
        list_GJ = new ArrayList<>();
        getDataFromServer();
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


    private void getDataFromServer() {
        getGJ();//工匠
    }

    private void getGJ() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                //.add("method", Server.HOME_CRAFTSMAN)
                .add("method", Server.HOME_HOT_CRAFTSMAN)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseGJ(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseGJ(String result) {
        Gson gson = new Gson();
        list_GJ = gson.fromJson(result, new TypeToken<List<HotCraftsman>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleGJ();
            }
        });
    }

    private void initRecycleGJ() {
        //设置网格布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        home_page_crafts_gv.setLayoutManager(gridLayoutManager);
        home_page_crafts_gv.setAdapter(new HotCraftsAdapter(getActivity(),list_GJ));
    }
}
