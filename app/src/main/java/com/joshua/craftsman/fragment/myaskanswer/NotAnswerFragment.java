package com.joshua.craftsman.fragment.myaskanswer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lister on 2017-06-15.
 */

public class NotAnswerFragment extends BaseFragment {

    @BindView(R.id.crafts_not_answer_lv)
    ListView crafts_not_answer_lv;

    private View mView;

    @Override
    public View initView() {
        mView = View.inflate(mContext, R.layout.my_ask_answer_crafts1, null);
        return mView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
