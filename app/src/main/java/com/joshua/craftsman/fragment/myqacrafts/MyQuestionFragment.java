package com.joshua.craftsman.fragment.myqacrafts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joshua.craftsman.R;
import com.joshua.craftsman.fragment.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Lister on 2017-06-15.
 */

public class MyQuestionFragment extends BaseFragment {

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.my_ask_answer_crafts3, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
