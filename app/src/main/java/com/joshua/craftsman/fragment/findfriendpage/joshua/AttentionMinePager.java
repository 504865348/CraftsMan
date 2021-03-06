package com.joshua.craftsman.fragment.findfriendpage.joshua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.craftsHome.CraftsHomeActivity;
import com.joshua.craftsman.adapter.find.FindFriendsAdapter;
import com.joshua.craftsman.entity.FindFriendsAttention;
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
 * 关注我的
 */
public class AttentionMinePager extends BaseFragment {

    @BindView(R.id.find_friends_recommend_rv)
    RecyclerView findFriendsRecommendRv;
    @BindView(R.id.empty_layout)
    FrameLayout empty_layout;
    private List<FindFriendsAttention> list_FF;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.find_friends_recommend_am, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
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
    public void onResume() {
        super.onResume();
        getDataFromServer();
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
                .add("method", Server.SERVER_ATTENTION_MINE)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                Log.d("关注", "关注我的: "+result);
                parseFF(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseFF(String result) {
        Gson gson = new Gson();
        list_FF = gson.fromJson(result, new TypeToken<List<FindFriendsAttention>>() {
        }.getType());
        if (list_FF.get(0).getCraftsmanName().equals("null")) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("关注", "关注我的: +empty");
                    setEmptyView(true);
                }
            });
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(false);
                    initRecycleFF();
                }
            });
        }
    }

    private void initRecycleFF() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findFriendsRecommendRv.setLayoutManager(linearLayoutManager);
        FindFriendsAdapter adapter = new FindFriendsAdapter(getActivity(),list_FF);
//        adapter.setOnRecyclerViewItemClickListener(new FindFriendsAdapter.onRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, String position) {
//                int pos = Integer.parseInt(position);
//                Intent intent = new Intent(mContext, CraftsHomeActivity.class);
//                intent.putExtra("craftsName", list_FF.get(pos).getCraftsmanName());
//                intent.putExtra("craftsAccount", list_FF.get(pos).getCraftsAccount());
//                intent.putExtra("craftsIntro", list_FF.get(pos).getIntroduction());
//                intent.putExtra("craftsClassify", list_FF.get(pos).getClassifyCrafts());
//                intent.putExtra("craftsHotDegree", list_FF.get(pos).getHotDegree());
//                intent.putExtra("craftsPic", list_FF.get(pos).getImageUrl());
//                mContext.startActivity(intent);
//            }
//        });
        findFriendsRecommendRv.setAdapter(adapter);
    }

    private void setEmptyView(Boolean isEmpty) {
        if(isEmpty){
            empty_layout.setVisibility(View.VISIBLE);
        }else {
            empty_layout.setVisibility(View.GONE);
        }
    }
}
