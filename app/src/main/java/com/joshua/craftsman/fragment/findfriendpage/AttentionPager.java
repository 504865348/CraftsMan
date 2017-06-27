package com.joshua.craftsman.fragment.findfriendpage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.find.FindFriendsAdapter;
import com.joshua.craftsman.entity.FindFriends;
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

public class AttentionPager extends BaseFragment {
    @BindView(R.id.find_friends_recommend_rv)
    RecyclerView findFriendsRecommendRv;

    private List<FindFriends> list_FF;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.find_friends_recommend, null);
        return view;
    }

    @Override
    public void initData() {
        list_FF = new ArrayList<>();
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
        getFF();
    }

    private void getFF() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.FIND_CIRCLE_FRIENDS)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseFF(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseFF(String result) {
        Gson gson = new Gson();
        list_FF = gson.fromJson(result, new TypeToken<List<FindFriends>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleFF();
            }
        });
    }

    private void initRecycleFF() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFriendsRecommendRv.setLayoutManager(linearLayoutManager);
        findFriendsRecommendRv.setAdapter(new FindFriendsAdapter(getActivity(),list_FF));
    }
}