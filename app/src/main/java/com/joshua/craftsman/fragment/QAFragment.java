package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.qaclassify.QAClassifyActivity;
import com.joshua.craftsman.adapter.HotCraftsAdapter;
import com.joshua.craftsman.adapter.QuesAnsClassifyAdapter;
import com.joshua.craftsman.entity.HotCraftsman;
import com.joshua.craftsman.entity.QuesAnsClassify;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class QAFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.hot_crafts_rv)
    RecyclerView hot_crafts_rv;
    @BindView(R.id.q_a_list_view_examples)
    RecyclerView q_a_list_view_examples;
    @BindView(R.id.q_a_construction)
    LinearLayout qAConstruction;
    @BindView(R.id.q_a_civilization)
    LinearLayout qACivilization;
    @BindView(R.id.q_a_electric)
    LinearLayout qAElectric;
    @BindView(R.id.q_a_road)
    LinearLayout qARoad;
    @BindView(R.id.q_a_water_cons)
    LinearLayout qAWaterCons;
    @BindView(R.id.q_a_railway)
    LinearLayout qARailway;
    @BindView(R.id.q_a_mining)
    LinearLayout qAMining;
    @BindView(R.id.q_a_airport)
    LinearLayout qAAirport;
    @BindView(R.id.q_a_communication)
    LinearLayout qACommunication;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private View view;
    private OkHttpClient mClient;
    private List<HotCraftsman> list_CJHR;
    private List<QuesAnsClassify> list_ques_ans;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.q_a, null);
        initRefreshRecycleView(view);
        return view;
    }
    private void initRefreshRecycleView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }
        });
    }
    @Override
    public void initData() {
        super.initData();
        initListener();
        getDataFromServer();
    }

    private void initListener() {
        qAConstruction.setOnClickListener(this);
        qACivilization.setOnClickListener(this);
        qAElectric.setOnClickListener(this);
        qARoad.setOnClickListener(this);
        qAWaterCons.setOnClickListener(this);
        qARailway.setOnClickListener(this);
        qAMining.setOnClickListener(this);
        qAAirport.setOnClickListener(this);
        qACommunication.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.q_a_construction:
                putExtraClassify("房屋建筑");
                break;
            case R.id.q_a_civilization:
                putExtraClassify("市政公用");
                break;
            case R.id.q_a_electric:
                putExtraClassify("机电工程");
                break;
            case R.id.q_a_road:
                putExtraClassify("公路");
                break;
            case R.id.q_a_water_cons:
                putExtraClassify("水利水电");
                break;
            case R.id.q_a_railway:
                putExtraClassify("铁路工程");
                break;
            case R.id.q_a_mining:
                putExtraClassify("矿业工程");
                break;
            case R.id.q_a_airport:
                putExtraClassify("民航机场");
                break;
            case R.id.q_a_communication:
                putExtraClassify("通信广电");
                break;
        }
    }

    private void putExtraClassify(String str) {
        Intent intent = new Intent(mContext, QAClassifyActivity.class);
        intent.putExtra("classifyFlag", str);
        mContext.startActivity(intent);
    }

    private void getDataFromServer() {
        mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        getCJHR();//超级红人
        getQuesAns();//问答

    }


    private void getCJHR() {
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
                    parseCJHR(result);
                }

            @Override
            protected void error() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mSwipeRefreshLayout.isRefreshing()){
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
            }
        });
    }

    private void getQuesAns() {
        RequestBody params = new FormBody.Builder()
                .add("method", Server.QUESANS_CLASSIFY)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                Log.d("jun", "getQuesAns: " + result);
                parseQuesAns(result);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mSwipeRefreshLayout.isRefreshing()){
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
            }

            @Override
               protected void error() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mSwipeRefreshLayout.isRefreshing()){
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
            }
        });
    }

    private void parseCJHR(String result) {
        Gson gson = new Gson();
        list_CJHR = gson.fromJson(result, new TypeToken<List<HotCraftsman>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initRecycleCJHR();
                }
            });
    }

    private void parseQuesAns(String result) {
        Gson gson = new Gson();
        list_ques_ans = gson.fromJson(result, new TypeToken<List<QuesAnsClassify>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initRecycleQuesAns();
                }
            });
    }

    private void initRecycleCJHR() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hot_crafts_rv.setLayoutManager(linearLayoutManager);
        hot_crafts_rv.setAdapter(new HotCraftsAdapter(getActivity(), list_CJHR));
    }

    private void initRecycleQuesAns() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        q_a_list_view_examples.setLayoutManager(linearLayoutManager);
        q_a_list_view_examples.setHasFixedSize(true);
        q_a_list_view_examples.setNestedScrollingEnabled(false);
        q_a_list_view_examples.setAdapter(new QuesAnsClassifyAdapter(getActivity(), list_ques_ans));
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

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
    }
}
