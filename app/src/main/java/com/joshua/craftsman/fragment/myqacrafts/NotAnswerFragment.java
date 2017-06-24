package com.joshua.craftsman.fragment.myqacrafts;

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
import com.joshua.craftsman.activity.ask.CraftsAnswerQuestionActivity;
import com.joshua.craftsman.adapter.qacrafts.NotAnswerAdapter;
import com.joshua.craftsman.entity.CraftsUnDealAns;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.utils.PrefUtils;

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
 * Created by Lister on 2017-06-15.
 */

public class NotAnswerFragment extends BaseFragment {

    @BindView(R.id.crafts_not_answer_rv)
    RecyclerView craftsNotAnswerRv;

    private View mView;
    private List<CraftsUnDealAns> list_WCL;

    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.my_ask_answer_crafts_undeal, null);
        return mView;
    }


    @Override
    public void initData() {
        list_WCL = new ArrayList<>();
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
        getWCL();
    }

    private void getWCL() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.CRAFTS_UNDEAL_ANS)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseWCL(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseWCL(String result) {
        Gson gson = new Gson();
        list_WCL = gson.fromJson(result, new TypeToken<List<CraftsUnDealAns>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleWCL();
            }
        });
    }

    private void initRecycleWCL() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        craftsNotAnswerRv.setLayoutManager(linearLayoutManager);
        NotAnswerAdapter notAnswerAdapter=new NotAnswerAdapter(getActivity(),list_WCL);
        notAnswerAdapter.setOnRecyclerViewItemClickListener(new NotAnswerAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String position) {
                Intent intent=new Intent(getActivity(),CraftsAnswerQuestionActivity.class);
                intent.putExtra("Id",list_WCL.get(Integer.parseInt(position)).getId());
                intent.putExtra("question",list_WCL.get(Integer.parseInt(position)).getContent());
                Log.d(TAG, "onItemClick: "+position);
                Log.d(TAG, "onItemClick: "+list_WCL.get(Integer.parseInt(position)).getId());
                startActivity(intent);
            }
        });
        craftsNotAnswerRv.setAdapter(notAnswerAdapter);

    }
}
