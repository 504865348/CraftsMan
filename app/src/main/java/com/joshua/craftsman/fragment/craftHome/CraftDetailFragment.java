package com.joshua.craftsman.fragment.craftHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.albumHome.AlbumHomeActivity;
import com.joshua.craftsman.activity.craftsHome.CraftsHomeActivity;
import com.joshua.craftsman.adapter.craftshome.CraftAlbumAdapter;
import com.joshua.craftsman.entity.CraftHomeAlbum;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CraftDetailFragment extends BaseFragment {

    @BindView(R.id.tv_detail)
    TextView tv_detail;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.craft_detail, null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        String info=getActivity().getIntent().getStringExtra("craftsIntro");
        tv_detail.setText(info);
        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}