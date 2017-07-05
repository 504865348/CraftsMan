package com.joshua.craftsman.fragment.findfriendpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joshua.craftsman.R;
import com.joshua.craftsman.fragment.BaseFragment;

import butterknife.ButterKnife;

public class AttentionPager extends BaseFragment {
   /* @BindView(R.id.find_friends_recommend_rv)
    RecyclerView findFriendsRecommendRv;

    private List<FindFriendsAttention> list_FF;*/

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.find_friends_recommend, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //list_FF = new ArrayList<>();
        //getDataFromServer();
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
    /*private void getDataFromServer() {
        getFF();
    }

    private void getFF() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.FIND_FRIENDS_ATTENTION)
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
        list_FF = gson.fromJson(result, new TypeToken<List<FindFriendsAttention>>() {
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
    }*/
}
