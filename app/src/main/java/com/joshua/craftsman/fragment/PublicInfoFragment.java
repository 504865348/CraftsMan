package com.joshua.craftsman.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.account.LoginActivity;
import com.joshua.craftsman.activity.answer.MyAskAnswerCommonActivity;
import com.joshua.craftsman.activity.feedback.FeedbackActivity;
import com.joshua.craftsman.activity.info.EditInfoActivity;
import com.joshua.craftsman.activity.set.SetActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PublicInfoFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.my_info_public_picture)
    ImageView mMyInfoPublicPicture;
    @BindView(R.id.my_info_public_more)
    RelativeLayout mMyInfoPublicMore;
    @BindView(R.id.my_info_public_subscribe)
    LinearLayout mMyInfoPublicSubscribe;
    @BindView(R.id.my_info_public_collection)
    LinearLayout mMyInfoPublicCollection;
    @BindView(R.id.my_info_public_my_orders)
    LinearLayout mMyInfoPublicMyOrders;
    @BindView(R.id.my_info_public_money)
    TextView mMyInfoPublicMoney;
    @BindView(R.id.my_info_public_my_coins)
    LinearLayout mMyInfoPublicMyCoins;
    @BindView(R.id.my_info_public_q_a_one)
    ImageView mMyInfoPublicQAOne;
    @BindView(R.id.my_info_public_my_q_a)
    LinearLayout mMyInfoPublicMyQA;
    @BindView(R.id.my_info_public_feedback)
    LinearLayout mMyInfoPublicFeedback;
    @BindView(R.id.my_info_public_sets)
    LinearLayout mMyInfoPublicSets;
    @BindView(R.id.my_info_public_user_name)
    TextView myInfoPublicUserName;
    @BindView(R.id.my_info_public_user_account)
    TextView myInfoPublicUserAccount;

    private View view;
    private Unbinder unbinder;
    private SharedPreferences sp;
    private String userClass = LoginActivity.appUserName;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.my_info_public, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mMyInfoPublicMore.setOnClickListener(this);
        mMyInfoPublicSubscribe.setOnClickListener(this);
        mMyInfoPublicCollection.setOnClickListener(this);
        showUserInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

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
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.my_info_public_sets:
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info_public_more:
                startActivity(new Intent(getActivity(), EditInfoActivity.class));
                break;
            case R.id.my_info_public_subscribe:
                //startActivity(new Intent(getActivity(), SubscribeActivity.class));
                Toast.makeText(getActivity(), "暂未开放专辑订阅功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_info_public_collection:
                //startActivity(new Intent(getActivity(), CollectActivity.class));
                Toast.makeText(getActivity(), "暂未开放节目收藏功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showUserInfo() {
        sp = getActivity().getSharedPreferences(userClass+".txt", Context.MODE_PRIVATE);
        if (sp.getString("nickName", "").equals(""))
            myInfoPublicUserName.setText(userClass);
        else
            myInfoPublicUserName.setText(sp.getString("nickName", ""));
        myInfoPublicUserAccount.setText(userClass);
        mMyInfoPublicPicture.setImageURI(Uri.fromFile(new File("/sdcard/craftsman/"+userClass+"/headImage.JPEG")));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
