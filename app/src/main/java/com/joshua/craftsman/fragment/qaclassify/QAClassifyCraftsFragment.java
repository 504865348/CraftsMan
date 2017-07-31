package com.joshua.craftsman.fragment.qaclassify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.qaclassify.QAClassifyActivity;
import com.joshua.craftsman.adapter.qafragment.ClassCraftAdapter;
import com.joshua.craftsman.entity.ClassCraft;
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
 * Created by nzz on 2017/7/12.
 * 问答Fragment-9大分类-工匠模块
 */

public class QAClassifyCraftsFragment extends BaseFragment {
    @BindView(R.id.question_answer_crafts_rv)
    RecyclerView questionAnswerCraftsRv;

    private List<ClassCraft> list_craft;
    private String classifyCrafts = QAClassifyActivity.classifyCraftsFlag;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.question_answer_class_crafts, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list_craft = new ArrayList<>();
        //getDataFromServer();
    }

    private void getDataFromServer() {
        getClassifyQues();
    }

    private void getClassifyQues() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.CLASSIFY_CRAFTS_LIST_BY_NAME)
                .add("classifyCrafts",classifyCrafts)
                .build();
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseClassifyQues(result);
            }
            @Override
            protected void error() {
            }
        });
    }
    private void parseClassifyQues(String result) {
        Gson gson = new Gson();
        list_craft = gson.fromJson(result, new TypeToken<List<ClassCraft>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initLayout();
            }
        });
    }
    private void initLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        questionAnswerCraftsRv.setLayoutManager(linearLayoutManager);
        questionAnswerCraftsRv.setAdapter(new ClassCraftAdapter(getActivity(), list_craft));
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
}
