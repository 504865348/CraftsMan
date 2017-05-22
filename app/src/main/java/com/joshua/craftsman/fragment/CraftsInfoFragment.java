package com.joshua.craftsman.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joshua.craftsman.R;

import butterknife.ButterKnife;

public class CraftsInfoFragment extends BaseFragment {

    private View view;

    /**
     * 初始化视图
     */
    @Override
    public View initView() {
        Log.e("TAG", "MyInfo --> initView()");
        view = View.inflate(mContext, R.layout.my_info_crafts, null);
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
