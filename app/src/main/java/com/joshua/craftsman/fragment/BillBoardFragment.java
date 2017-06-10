package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.billboard.*;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillBoardFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.billboard_hot_more) ImageView billboardHotMore;
    @BindView(R.id.billboard_more_more) ImageView billboardMoreMore;
    @BindView(R.id.billboard_pay_more) ImageView billboardPayMore;
    @BindView(R.id.billboard_crafts_more) ImageView billboardCraftsMore;

    private View view;

    @Override
    public View initView() {
        Log.e("TAG", "BillBoard-->initView()");
        view = View.inflate(mContext, R.layout.billboard, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        billboardHotMore.setOnClickListener(this);
        billboardMoreMore.setOnClickListener(this);
        billboardPayMore.setOnClickListener(this);
        billboardCraftsMore.setOnClickListener(this);
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
            case R.id.billboard_hot_more:
                startActivity(new Intent(getActivity(), BillboardHotActivity.class));
                break;
            case R.id.billboard_more_more:
                startActivity(new Intent(getActivity(), BillboardMoreActivity.class));
                break;
            case R.id.billboard_pay_more:
                startActivity(new Intent(getActivity(), BillboardPayActivity.class));
                break;
            case R.id.billboard_crafts_more:
                startActivity(new Intent(getActivity(), BillboardCraftsmanActivity.class));
                break;
        }
    }
}
