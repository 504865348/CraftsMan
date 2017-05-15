package com.joshua.craftsman.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joshua.craftsman.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 陈天然 on 2017-05-15.
 * 首页 Fragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.main_text_search) TextView mTextSearch;
    @BindView(R.id.main_image_history) ImageView mImageHistory;
    @BindView(R.id.main_image_download) ImageView mImageDownload;

    private View view;

    /**
     * 初始化视图
     */
    @Override
    public View initView() {
        Log.e("TAG", "Home-->initView()");
        view = View.inflate(mContext, R.layout.home_page, null);
        return view;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
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
