package com.joshua.craftsman.fragment.homepage;

import android.content.Context;
import android.content.Intent;
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
import com.joshua.craftsman.activity.record.PlayerFrameActivity;
import com.joshua.craftsman.adapter.HomeRecommendAdapter;
import com.joshua.craftsman.entity.BillboardHot;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.media.CamcorderProfile.get;

public class HomeRecommendPager extends BaseFragment {


    @BindView(R.id.home_recommend_rv)

    RecyclerView home_recommend_rv;

    private List<BillboardHot> list_TJ;
    private Context mContext;

    public HomeRecommendPager(Context context) {
        mContext = context;
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.home_page_recommend, null);
        return view;
    }

    @Override
    public void initData() {
        list_TJ = new ArrayList<>();
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
        getTJ();//推荐
    }

    private void getTJ() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.BILLBOARD_HOT)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseTJ(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseTJ(String result) {
        Gson gson = new Gson();
        list_TJ = gson.fromJson(result, new TypeToken<List<BillboardHot>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleTJ();
            }
        });
    }

    private void initRecycleTJ() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        home_recommend_rv.setLayoutManager(linearLayoutManager);
        HomeRecommendAdapter adapter = new HomeRecommendAdapter(getActivity(), list_TJ);
        adapter.setOnRecyclerViewItemClickListener(new HomeRecommendAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String position) {
                int pos = Integer.parseInt(position);
                Log.d(TAG, "onItemClick: " + pos);
                String url = list_TJ.get(pos).getDownloadUrl();
                Intent intent=new Intent(mContext,PlayerFrameActivity.class);
                intent.putExtra("url",url);
                Log.d(TAG, "onItemClick: "+url);
//                startActivity(intent);
                startFullScreen(url);

            }
        });
        home_recommend_rv.setAdapter(adapter);
    }


    public void startFullScreen(String url) {
        JCVideoPlayerStandard.startFullscreen(mContext,
                JCVideoPlayerStandard.class,
                url);
    }

}
