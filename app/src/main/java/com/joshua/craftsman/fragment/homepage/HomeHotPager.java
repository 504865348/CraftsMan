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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.hot.CraftsActivity;
import com.joshua.craftsman.activity.hot.ListenActivity;
import com.joshua.craftsman.activity.hot.LookActivity;
import com.joshua.craftsman.activity.hot.PolicyActivity;
import com.joshua.craftsman.activity.hot.SkillsActivity;
import com.joshua.craftsman.adapter.HotCraftsAdapter;
import com.joshua.craftsman.adapter.HotListenAdapter;
import com.joshua.craftsman.adapter.HotLookAdapter;
import com.joshua.craftsman.adapter.HotPolicyAdapter;
import com.joshua.craftsman.adapter.HotSkillsAdapter;
import com.joshua.craftsman.entity.CarouselPic;
import com.joshua.craftsman.entity.HotCraftsman;
import com.joshua.craftsman.entity.HotListen;
import com.joshua.craftsman.entity.HotLook;
import com.joshua.craftsman.entity.HotPolicy;
import com.joshua.craftsman.entity.HotSkills;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.http.glide.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class HomeHotPager extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.home_page_hot_banner)
    Banner home_page_hot_banner;
    @BindView(R.id.hot_crafts_rv)
    RecyclerView hot_crafts_rv;
    @BindView(R.id.hot_skills_rv)
    RecyclerView hot_skills_rv;
    @BindView(R.id.hot_policy_rv)
    RecyclerView hot_policy_rv;
    @BindView(R.id.hot_listen_rv)
    RecyclerView hot_listen_rv;
    @BindView(R.id.hot_look_rv)
    RecyclerView hot_look_rv;
    @BindView(R.id.hot_middle_image_crafts)
    ImageView hotMiddleImageCrafts;
    @BindView(R.id.hot_middle_image_skills)
    ImageView hotMiddleImageSkills;
    @BindView(R.id.hot_middle_image_policy)
    ImageView hotMiddleImagePolicy;
    @BindView(R.id.hot_middle_image_listen)
    ImageView hotMiddleImageListen;
    @BindView(R.id.hot_middle_image_look)
    ImageView hotMiddleImageLook;
    @BindView(R.id.home_page_hot_more_crafts)
    TextView homePageHotMoreCrafts;
    @BindView(R.id.home_page_hot_more_skills)
    TextView homePageHotMoreSkills;
    @BindView(R.id.home_page_hot_more_policy)
    TextView homePageHotMorePolicy;
    @BindView(R.id.home_page_hot_more_listen)
    TextView homePageHotMoreListen;
    @BindView(R.id.home_page_hot_more_look)
    TextView homePageHotMoreLook;

    private List<CarouselPic> list_pic;
    private List<HotCraftsman> list_DGGJ;
    private List<HotSkills> list_JXDY;
    private List<HotPolicy> list_JZC;
    private List<HotListen> list_TZT;
    private List<HotLook> list_KLQ;
    private OkHttpClient mClient;

    private Context mContext;

    public HomeHotPager(Context context) {
        this.mContext=context;
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.home_page_hot, null);
    }

    @Override
    public void initData() {
        list_pic = new ArrayList<>();
        list_DGGJ = new ArrayList<>();
        list_JXDY = new ArrayList<>();
        list_JZC = new ArrayList<>();
        list_TZT = new ArrayList<>();
        list_KLQ = new ArrayList<>();
        initListener();
        getDataFromServer();
    }

    private void initListener() {
        hotMiddleImageCrafts.setOnClickListener(this);
        hotMiddleImageSkills.setOnClickListener(this);
        hotMiddleImagePolicy.setOnClickListener(this);
        hotMiddleImageListen.setOnClickListener(this);
        hotMiddleImageLook.setOnClickListener(this);
        homePageHotMoreCrafts.setOnClickListener(this);
        homePageHotMoreSkills.setOnClickListener(this);
        homePageHotMorePolicy.setOnClickListener(this);
        homePageHotMoreListen.setOnClickListener(this);
        homePageHotMoreLook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hot_middle_image_crafts:
                startActivity(new Intent(getActivity(), CraftsActivity.class));
                break;
            case R.id.home_page_hot_more_crafts:
                startActivity(new Intent(getActivity(), CraftsActivity.class));
                break;
            case R.id.hot_middle_image_skills:
                startActivity(new Intent(getActivity(), SkillsActivity.class));
                break;
            case R.id.home_page_hot_more_skills:
                startActivity(new Intent(getActivity(), SkillsActivity.class));
                break;
            case R.id.hot_middle_image_policy:
                startActivity(new Intent(getActivity(), PolicyActivity.class));
                break;
            case R.id.home_page_hot_more_policy:
                startActivity(new Intent(getActivity(), PolicyActivity.class));
                break;
            case R.id.hot_middle_image_listen:
                startActivity(new Intent(getActivity(), ListenActivity.class));
                break;
            case R.id.home_page_hot_more_listen:
                startActivity(new Intent(getActivity(), ListenActivity.class));
                break;
            case R.id.hot_middle_image_look:
                startActivity(new Intent(getActivity(), LookActivity.class));
                break;
            case R.id.home_page_hot_more_look:
                startActivity(new Intent(getActivity(), LookActivity.class));
                break;
        }
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
        mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        getBanner();//轮播图
        getDGGJ();//大国工匠
        getJXDY();//匠心独运
        getJZC();//讲政策
        getTZT();//听专题
        getKLQ();//看利器

    }

    /**
     * --------------------------------数据获取---------------------------------
     */
    private void getDGGJ() {
//        mClient = new OkHttpClient.Builder()
//                .cookieJar(new HttpCookieJar(getActivity()))
//                .build();
        RequestBody params = new FormBody.Builder()
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
                parseDGGJ(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void getJXDY() {
//        mClient = new OkHttpClient.Builder()
//                .cookieJar(new HttpCookieJar(getActivity()))
//                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_SKILLS)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseJXDY(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void getJZC() {
//        mClient = new OkHttpClient.Builder()
//                .cookieJar(new HttpCookieJar(getActivity()))
//                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_POLICY)
                .build();
        Log.d(TAG, "getJZC: " + Server.HOME_HOT_POLICY);

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseJZC(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void getTZT() {
//        OkHttpClient mClient = new OkHttpClient.Builder()
//                .cookieJar(new HttpCookieJar(getActivity()))
//                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_LISTEN)
                .build();
        Log.d(TAG, "getTZT: " + Server.HOME_HOT_LISTEN);
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseTZT(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void getKLQ() {
//        OkHttpClient mClient = new OkHttpClient.Builder()
//                .cookieJar(new HttpCookieJar(getActivity()))
//                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.HOME_HOT_LOOK)
                .build();
        Log.d(TAG, "getKLQ: " + Server.HOME_HOT_LOOK);
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {

                parseKLQ(result);
            }

            @Override
            protected void error() {
            }
        });
    }


    /**
     * --------------------------------数据解析---------------------------------
     */
    private void parseDGGJ(String result) {
        Gson gson = new Gson();
        list_DGGJ = gson.fromJson(result, new TypeToken<List<HotCraftsman>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleDGGJ();
            }
        });
    }

    private void parseJXDY(String result) {
        Gson gson = new Gson();
        list_JXDY = gson.fromJson(result, new TypeToken<List<HotSkills>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleJXDY();
            }
        });
    }

    private void parseJZC(String result) {
        Gson gson = new Gson();
        list_JZC = gson.fromJson(result, new TypeToken<List<HotPolicy>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleJZC();
            }
        });
    }

    private void parseTZT(String result) {
        Gson gson = new Gson();
        list_TZT = gson.fromJson(result, new TypeToken<List<HotListen>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleTZT();
            }
        });
    }

    private void parseKLQ(String result) {
        Gson gson = new Gson();
        list_KLQ = gson.fromJson(result, new TypeToken<List<HotLook>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleKLQ();
            }
        });
    }

    /**
     * --------------------------------数据展示---------------------------------
     */
    private void initRecycleDGGJ() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hot_crafts_rv.setLayoutManager(linearLayoutManager);
        hot_crafts_rv.setAdapter(new HotCraftsAdapter(getActivity(), list_DGGJ));
    }

    private void initRecycleJXDY() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hot_skills_rv.setLayoutManager(linearLayoutManager);
        hot_skills_rv.setAdapter(new HotSkillsAdapter(getActivity(), list_JXDY));
    }

    private void initRecycleJZC() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hot_policy_rv.setLayoutManager(linearLayoutManager);
        hot_policy_rv.setAdapter(new HotPolicyAdapter(getActivity(), list_JZC));
    }

    private void initRecycleTZT() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hot_listen_rv.setLayoutManager(linearLayoutManager);
        hot_listen_rv.setAdapter(new HotListenAdapter(getActivity(), list_TZT));
    }

    private void initRecycleKLQ() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hot_look_rv.setLayoutManager(linearLayoutManager);
        hot_look_rv.setAdapter(new HotLookAdapter(getActivity(), list_KLQ));
    }

    /**
     * --------------------------------轮播图---------------------------------
     */
    private void getBanner() {
//        OkHttpClient mClient = new OkHttpClient.Builder()
//                .cookieJar(new HttpCookieJar(getActivity()))
//                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.CAROUSEL_PIC)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseBanner(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    /**
     * 解析JSON数据-banner
     *
     * @param result
     */
    private void parseBanner(String result) {
        Gson gson = new Gson();
        list_pic = gson.fromJson(result, new TypeToken<List<CarouselPic>>() {
        }.getType());
        List<String> imageUrls = new ArrayList<>();
        List<String> imageNames = new ArrayList<>();
        for (CarouselPic carouselPic : list_pic) {
            imageUrls.add(carouselPic.getImgUrl());
            imageNames.add(carouselPic.getImgName());
        }
        initBanner(imageUrls, imageNames);

    }

    /**
     * 初始化轮播图
     */
    private void initBanner(final List<String> imageUrls, final List<String> imageNames) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                home_page_hot_banner
                        .setImages(imageUrls)
                        .setBannerTitles(imageNames)
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setBannerAnimation(Transformer.Tablet)
                        .setImageLoader(new GlideImageLoader())
                        .setDelayTime(5000)
                        .start();
            }
        });

    }
}
