package com.joshua.craftsman.fragment.myqacrafts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.qacrafts.AnsAdapter;
import com.joshua.craftsman.adapter.qacrafts.NotAnswerAdapter;
import com.joshua.craftsman.entity.CraftsMyAns;
import com.joshua.craftsman.entity.CraftsUnDealAns;
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
 * Created by Lister on 2017-06-15.
 */

public class MyAnswerFragment extends BaseFragment {

    @BindView(R.id.crafts_ans_rv)
    RecyclerView craftsAnsRv;

    private List<CraftsMyAns> list_ANS;
    private View mView;

    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.my_ask_answer_crafts_ans, null);
        return mView;
    }

    @Override
    public void initData() {
        list_ANS = new ArrayList<>();
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
        getANS();
    }

    private void getANS() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.CRAFTS_MY_ANS)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseANS(result);
            }

            @Override
            protected void error() {

            }
        });
    }

    private void parseANS(String result) {
        Gson gson = new Gson();
        list_ANS = gson.fromJson(result, new TypeToken<List<CraftsMyAns>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycleANS();
            }
        });
    }

    private void initRecycleANS() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        craftsAnsRv.setLayoutManager(linearLayoutManager);
        craftsAnsRv.setAdapter(new AnsAdapter(getActivity(),list_ANS));
    }
}