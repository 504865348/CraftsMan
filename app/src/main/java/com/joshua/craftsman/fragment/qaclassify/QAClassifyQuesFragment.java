package com.joshua.craftsman.fragment.qaclassify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joshua.craftsman.R;
import com.joshua.craftsman.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by nzz on 2017/7/12.
 * 问答Fragment-9大分类-问答模块
 */

public class QAClassifyQuesFragment extends BaseFragment{
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.question_answer_class_ques, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
