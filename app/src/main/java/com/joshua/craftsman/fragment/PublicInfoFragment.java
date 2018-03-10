package com.joshua.craftsman.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.activity.account.LoginActivity;
import com.joshua.craftsman.activity.answer.MyAskAnswerCommonActivity;
import com.joshua.craftsman.activity.error.DataErrorActivity;
import com.joshua.craftsman.activity.feedback.FeedbackActivity;
import com.joshua.craftsman.activity.info.EditInfoActivity;
import com.joshua.craftsman.activity.info.SubscribeActivity;
import com.joshua.craftsman.activity.order.MyOrderActivity;
import com.joshua.craftsman.activity.other.MyBuyActivity;
import com.joshua.craftsman.activity.other.MyCollectActivity;
import com.joshua.craftsman.activity.other.MySubscribeActivity;
import com.joshua.craftsman.activity.set.SetActivity;
import com.joshua.craftsman.entity.MySubscribe;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.http.ResponseInfo;
import com.joshua.craftsman.utils.PrefUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.joshua.craftsman.R.id.my_info_my_buys;

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
    @BindView(R.id.my_info_my_buys)
    LinearLayout mMyInfoBuy;

    private View view;
    private Unbinder unbinder;
    private SharedPreferences sp;
    private String userClass;

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
        mMyInfoBuy.setOnClickListener(this);
        userClass = PrefUtils.getString(getActivity(), "phone", null);
        showUserInfo();
        checkCookie();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        showUserInfo();
        Log.d(TAG, "setUserVisibleHint: "+"load image");
    }


    private void checkCookie() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.COMMON_UNDEAL_ANS)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseJson = response.body().string();
                Log.d(TAG, "onResponse: " + responseJson);
                Gson gson = new Gson();
                ResponseInfo responseInfo = gson.fromJson(responseJson, ResponseInfo.class);
                if (!responseInfo.isAlive()) {
                    Log.d(TAG, "onResponse: cookie过期");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                else if (responseInfo.isError()) {
                    Log.d(TAG, "onResponse: 出现异常");
                    startActivity(new Intent(getActivity(), DataErrorActivity.class));
                } else {
                    String result = responseInfo.getResult();
                    Log.d(TAG, "onResponseAgain: " + result);
                }
            }


        });
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
                mContext.startActivity(new Intent(getActivity(), MyOrderActivity.class));
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
    public void onResume() {
        super.onResume();
        showUserInfo();
        checkCookie();
        Log.d(TAG, "onResume: "+"load image");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info_public_more:
                startActivityForResult(new Intent(getActivity(), EditInfoActivity.class),1);
                break;
            case R.id.my_info_public_subscribe:
                startActivity(new Intent(getActivity(), MySubscribeActivity.class));
                break;
            case R.id.my_info_public_collection:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case my_info_my_buys:
                startActivity(new Intent(getActivity(), MyBuyActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showUserInfo();
    }

    private void showUserInfo() {

        sp = getActivity().getSharedPreferences(userClass, Context.MODE_PRIVATE);
        if (sp.getString("nickName", "").isEmpty())
            myInfoPublicUserName.setText(userClass);
        else
            myInfoPublicUserName.setText(sp.getString("nickName", ""));
        myInfoPublicUserAccount.setText(userClass);
        mMyInfoPublicPicture.setImageURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/craftsman/" + PrefUtils.getString(getActivity(), "phone", "") + "/headImage.JPEG")));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
