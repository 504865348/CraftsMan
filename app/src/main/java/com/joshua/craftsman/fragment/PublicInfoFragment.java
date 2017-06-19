package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.answer.MyAskAnswerCommonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PublicInfoFragment extends BaseFragment {

    @BindView(R.id.my_info_public_picture) ImageView mMyInfoPublicPicture;
    @BindView(R.id.my_info_public_name) TextView mMyInfoPublicName;
    @BindView(R.id.my_info_public_following) TextView mMyInfoPublicFollowing;
    @BindView(R.id.public_next) ImageButton mPublicNext;
    @BindView(R.id.my_info_public_more) RelativeLayout mMyInfoPublicMore;
    @BindView(R.id.my_info_public_subscribe) LinearLayout mMyInfoPublicSubscribe;
    @BindView(R.id.my_info_public_collection) LinearLayout mMyInfoPublicCollection;
    @BindView(R.id.my_info_public_my_orders) LinearLayout mMyInfoPublicMyOrders;
    @BindView(R.id.my_info_public_money) TextView mMyInfoPublicMoney;
    @BindView(R.id.my_info_public_my_coins) LinearLayout mMyInfoPublicMyCoins;
    @BindView(R.id.my_info_public_q_a_one) ImageView mMyInfoPublicQAOne;
    @BindView(R.id.my_info_public_my_q_a) LinearLayout mMyInfoPublicMyQA;
    @BindView(R.id.my_info_public_feedback) LinearLayout mMyInfoPublicFeedback;
    @BindView(R.id.my_info_public_sets) LinearLayout mMyInfoPublicSets;

    private View view;
    private Unbinder unbinder;

    /**
     * LinearLayout 点击事件
     */
    @OnClick({R.id.my_info_public_my_orders, R.id.my_info_public_my_coins, R.id.my_info_public_my_q_a,
            R.id.my_info_public_feedback, R.id.my_info_public_sets})
    public void clickLinearLayout(LinearLayout linearLayout) {
        switch (linearLayout.getId()) {
            case R.id.my_info_public_my_orders:
                break;
            case R.id.my_info_public_my_coins:
                break;
            case R.id.my_info_public_my_q_a:
                mContext.startActivity(new Intent(getActivity(), MyAskAnswerCommonActivity.class));
                break;
            case R.id.my_info_public_feedback:
                break;
            case R.id.my_info_public_sets:
                break;
        }
    }


    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.my_info_public, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
