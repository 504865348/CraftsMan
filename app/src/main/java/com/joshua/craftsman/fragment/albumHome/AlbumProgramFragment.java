package com.joshua.craftsman.fragment.albumHome;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.albumHome.AlbumHomeActivity;
import com.joshua.craftsman.adapter.albumhome.AlbumProgramAdapter;
import com.joshua.craftsman.entity.AlbumHomePro;
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
 * Created by nzz on 2017/6/20.
 * 专辑主页-节目Fragment
 */

public class AlbumProgramFragment extends BaseFragment {


    @BindView(R.id.album_program_download)
    ImageView albumProgramDownload;
    @BindView(R.id.album_pro_rv)
    RecyclerView albumProRv;
    @BindView(R.id.album_pro_number)
    TextView albumProNumber;

    private List<AlbumHomePro> list_albumPro;
    private String albumId = AlbumHomeActivity.homeAlbumId;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.album_home_program, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list_albumPro = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getAlbumPro();
    }

    private void getAlbumPro() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.PRO_LIST_BY_NAME)
                .add("albumId", albumId)
                .build();
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseAlbumPro(result);
            }

            @Override
            protected void error() {
            }
        });

    }

    private void parseAlbumPro(String result) {
        Gson gson = new Gson();
        list_albumPro = gson.fromJson(result, new TypeToken<List<AlbumHomePro>>() {
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
        albumProRv.setLayoutManager(linearLayoutManager);
        albumProRv.setAdapter(new AlbumProgramAdapter(getActivity(), list_albumPro));
        albumProNumber.setText(String.valueOf(linearLayoutManager.getItemCount()));
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
