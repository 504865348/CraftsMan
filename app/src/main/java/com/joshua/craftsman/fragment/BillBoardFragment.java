package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.billboard.BillboardCraftsmanActivity;
import com.joshua.craftsman.activity.billboard.BillboardHotActivity;
import com.joshua.craftsman.activity.billboard.BillboardMoreActivity;
import com.joshua.craftsman.activity.billboard.BillboardPayActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillBoardFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.billboard_hot)
    RelativeLayout billboardHot;
    @BindView(R.id.billboard_more)
    RelativeLayout billboardMore;
    @BindView(R.id.billboard_pay)
    RelativeLayout billboardPay;
    @BindView(R.id.billboard_craftsman)
    RelativeLayout billboardCraftsman;

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
        billboardHot.setOnClickListener(this);
        billboardMore.setOnClickListener(this);
        billboardPay.setOnClickListener(this);
        billboardCraftsman.setOnClickListener(this);
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
            case R.id.billboard_hot:
                startActivity(new Intent(getActivity(), BillboardHotActivity.class));
                break;
            case R.id.billboard_more:
                startActivity(new Intent(getActivity(), BillboardMoreActivity.class));
                break;
            case R.id.billboard_pay:
                startActivity(new Intent(getActivity(), BillboardPayActivity.class));
                break;
            case R.id.billboard_craftsman:
                startActivity(new Intent(getActivity(), BillboardCraftsmanActivity.class));
                break;
        }
    }
}
