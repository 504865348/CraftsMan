package com.joshua.craftsman.fragment.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joshua.craftsman.R;
import com.joshua.craftsman.fragment.BaseFragment;

import butterknife.ButterKnife;

public class HomeCraftsPager extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.home_page_crafts, null);
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
