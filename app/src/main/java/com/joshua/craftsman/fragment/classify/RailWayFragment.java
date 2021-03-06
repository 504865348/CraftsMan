package com.joshua.craftsman.fragment.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.albumHome.AlbumHomeActivity;
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
 * 首页-分类-铁路工程Fragment
 */

public class RailWayFragment extends BaseFragment {

    @BindView(R.id.classify_railWay_rv)
    RecyclerView classifyRailWayRv;

    private List<Classify> list_railWay;

    public static Fragment newInstance() {
        RailWayFragment fragment = new RailWayFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.classify_railway, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list_railWay = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getRailWay("城市管道");
    }

    private void getRailWay(String keyWord) {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        String requestStr = "method=" + Server.HOME_CLASSIFY + "&keyWord=" + keyWord;
        RequestBody params = RequestBody.create(Server.MEDIA_TYPE_MARKDOWN,requestStr);
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseRailWay(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseRailWay(String result) {
        Gson gson = new Gson();
        list_railWay = gson.fromJson(result, new TypeToken<List<Classify>>() {
        }.getType());
        if (list_railWay.get(0).getTitle().equals("null")) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(true);
                }
            });
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(false);
                    initRecycleRailWay();
                }
            });
        }
    }

    private void initRecycleRailWay() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        classifyRailWayRv.setLayoutManager(linearLayoutManager);
        ClassifyAdapter adapter = new ClassifyAdapter(getActivity(), list_railWay);
        adapter.setOnRecyclerViewItemClickListener(new ClassifyAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String position) {
                int pos = Integer.parseInt(position);
                Intent intent = new Intent(mContext, AlbumHomeActivity.class);
                intent.putExtra("albumId", list_railWay.get(pos).getId());
                intent.putExtra("albumName", list_railWay.get(pos).getTitle());
                intent.putExtra("albumPic", list_railWay.get(pos).getAlbumImage());
                intent.putExtra("albumCrafts", list_railWay.get(pos).getCraftsmanName());
                intent.putExtra("albumIntroduction", list_railWay.get(pos).getIntroduction());
                intent.putExtra("albumClassify", list_railWay.get(pos).getClassify());
                intent.putExtra("albumModel", list_railWay.get(pos).getModel());
                intent.putExtra("albumPlay", list_railWay.get(pos).getPlay());
                intent.putExtra("albumSubscribe", list_railWay.get(pos).getSubscribe());
                mContext.startActivity(intent);
            }
        });
        classifyRailWayRv.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void setEmptyView(Boolean isEmpty) {
        FrameLayout empty= (FrameLayout) getActivity().findViewById(R.id.empty_classify_f);
        if(isEmpty){
            empty.setVisibility(View.VISIBLE);
        }else {
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
