package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.joshua.craftsman.activity.download.DownloadActivity;
import com.joshua.craftsman.activity.history.HistoryActivity;
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

import static com.joshua.craftsman.R.id.hot_crafts_rv;
import static com.joshua.craftsman.R.id.list;

public class QAFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.q_a_download)
    LinearLayout mLinear_download;
    @BindView(R.id.q_a_history)
    LinearLayout mLinear_history;
    @BindView(R.id.hot_crafts_rv)
    RecyclerView hot_crafts_rv;
    @BindView(R.id.q_a_list_view_examples)
    RecyclerView q_a_list_view_examples;

    private View view;
    private OkHttpClient mClient;
    private List<HotCraftsman> list_CJHR;
    private List<QuesAnsClassify> list_ques_ans;
    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.q_a, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mLinear_download.setOnClickListener(this);
        mLinear_history.setOnClickListener(this);
        getDataFromServer();

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
                parseQuesAns(result);
            }

            @Override
            protected void error() {

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
        hot_crafts_rv.setAdapter(new HotCraftsAdapter(getActivity(),list_CJHR));
    }
    private void initRecycleQuesAns() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        q_a_list_view_examples.setLayoutManager(linearLayoutManager);
        q_a_list_view_examples.setAdapter(new QuesAnsClassifyAdapter(getActivity(),list_ques_ans));
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.q_a_download:
                startActivity(new Intent(getActivity(), DownloadActivity.class));
                break;
            case R.id.q_a_history:
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
        }
    }
}
