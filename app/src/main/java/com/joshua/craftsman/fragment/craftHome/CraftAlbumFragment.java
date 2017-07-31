package com.joshua.craftsman.fragment.craftHome;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
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
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CraftAlbumFragment extends BaseFragment {

    @BindView(R.id.craft_album_rv)
    RecyclerView craftAlbumRv;

    private List<CraftHomeAlbum> list_album;
    private String craftsName = CraftsHomeActivity.homeCraftsName;
    public String str;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.craft_album, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list_album = new ArrayList<>();
        getDataFromServer();
    }

    private void getDataFromServer() {
        getCraftAlbum();
    }

    private void getCraftAlbum() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.ALBUM_LIST_BY_CRAFTS)
                .add("craftsName",craftsName)
                .build();
        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                parseCraftAlbum(result);
            }
            @Override
            protected void error() {
            }
        });
    }
    private void parseCraftAlbum(String result) {
        Gson gson = new Gson();
        list_album = gson.fromJson(result, new TypeToken<List<CraftHomeAlbum>>() {
        }.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initLayout();
            }
        });
        /*if(list_album.get(0).getTitle().equals("null")) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(true);
                }
            });
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(false);
                    initLayout();
                }
            });
        }*/
    }
    private void initLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        craftAlbumRv.setLayoutManager(linearLayoutManager);
        craftAlbumRv.setAdapter(new CraftAlbumAdapter(getActivity(), list_album));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void setEmptyView(Boolean isEmpty) {
        FrameLayout empty= (FrameLayout) getActivity().findViewById(R.id.empty_layout);
        if(isEmpty){
            empty.setVisibility(View.VISIBLE);
        }else {
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}