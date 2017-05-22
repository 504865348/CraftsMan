package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.download.DownloadActivity;
import com.joshua.craftsman.activity.history.HistoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QAFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.q_a_download) LinearLayout mLinear_download;
    @BindView(R.id.q_a_history) LinearLayout mLinear_history;

    private View view;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.q_a, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mLinear_download.setOnClickListener(this);
        mLinear_history.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.q_a_download:
                startActivity(new Intent(getActivity(), DownloadActivity.class));
                break;
            case R.id.q_a_history:
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
        }
    }
}
