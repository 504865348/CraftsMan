package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.account.LoginActivity;
import com.joshua.craftsman.activity.order.MyOrderActivity;
import com.joshua.craftsman.activity.account.MoneyActivity;
import com.joshua.craftsman.activity.answer.MyAskAnswerActivity;
import com.joshua.craftsman.activity.feedback.FeedbackActivity;
import com.joshua.craftsman.activity.info.EditInfoActivity;
import com.joshua.craftsman.activity.record.MyRecordActivity;
import com.joshua.craftsman.activity.set.SetActivity;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.joshua.craftsman.R.id.my_info_my_coins;

public class CraftsInfoFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.my_info_crafts_record) RelativeLayout btn_record;
    @BindView(R.id.my_info_my_records) LinearLayout btn_my_record;
    @BindView(R.id.my_info_my_q_a) LinearLayout my_info_my_q_a;
    @BindView(R.id.my_info_picture) ImageView mMyInfoPicture;
    @BindView(R.id.my_info_user_name) TextView mMyInfoUserName;
    @BindView(R.id.my_info_following) TextView mMyInfoFollowing;
    @BindView(R.id.my_info_followers) TextView mMyInfoFollowers;
    @BindView(R.id.next) ImageButton mNext;
    @BindView(R.id.my_info_more) RelativeLayout mMyInfoMore;
    @BindView(R.id.my_info_subscribe) LinearLayout mMyInfoSubscribe;
    @BindView(R.id.my_info_collection) LinearLayout mMyInfoCollection;
    @BindView(R.id.text_start_record) TextView mTextStartRecord;
    @BindView(R.id.text_start_upload) TextView mTextStartUpload;
    @BindView(R.id.my_info_crafts_upload) RelativeLayout mMyInfoCraftsUpload;
    @BindView(R.id.orderform) ImageButton mOrderform;
    @BindView(R.id.my_info_my_orders) LinearLayout mMyInfoMyOrders;
    @BindView(R.id.my_info_money) TextView mMyInfoMoney;
    @BindView(R.id.money) ImageButton mMoney;
    @BindView(R.id.my_info_my_coins) LinearLayout mMyInfoMyCoins;
    @BindView(R.id.my_info_q_a_one) ImageView mMyInfoQAOne;
    @BindView(R.id.question) ImageButton mQuestion;
    @BindView(R.id.album) ImageButton mAlbum;
    @BindView(R.id.my_info_my_albums) LinearLayout mMyInfoMyAlbums;
    @BindView(R.id.list) ImageButton mList;
    @BindView(R.id.my_info_my_billboard) LinearLayout mMyInfoMyBillboard;
    @BindView(R.id.recorder) ImageButton mRecorder;
    @BindView(R.id.opinion) ImageButton mOpinion;
    @BindView(R.id.my_info_feedback) LinearLayout mMyInfoFeedback;
    @BindView(R.id.set) ImageButton mSet;
    @BindView(R.id.my_info_sets) LinearLayout mMyInfoSets;

    private View view;
    private Uri fileUri;
    private int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 1;
    //private SharedPreferences sp;
    private static final String userClass = LoginActivity.appUserName;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.my_info_crafts, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        mMyInfoMore.setOnClickListener(this);
        mMyInfoFeedback.setOnClickListener(this);
        mMyInfoSets.setOnClickListener(this);
        mMyInfoCraftsUpload.setOnClickListener(this);
        //showUserName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.my_info_crafts_record)
    public void startRecord() {
        try {
            fileUri = Uri.fromFile(createMediaFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        Intent intent = new Intent();
        intent.setAction("android.media.action.VIDEO_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // 设置视频文件的名字
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // 设置视频质量为高
        startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
    }

    @OnClick(R.id.my_info_my_orders)
    public void myOrders() {
        startActivity(new Intent(getActivity(), MyOrderActivity.class));
    }

    @OnClick(R.id.my_info_my_records)
    public void myRecord() {
        startActivity(new Intent(getActivity(), MyRecordActivity.class));
    }

    @OnClick(R.id.my_info_my_q_a)
    public void my_q_a() {
        startActivity(new Intent(getActivity(), MyAskAnswerActivity.class));
    }

    @OnClick(my_info_my_coins)
    public void my_coins() {
        startActivity(new Intent(getActivity(), MoneyActivity.class));
    }


    /**
     * 创建一个媒体对象
     */
    private File createMediaFile() throws IOException {
        /**
         * 如果 SD 卡存在，则在外部存储建立一个文件夹用于存放视频
         */
        if ((Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))) {
            /**
             * 选择自己的文件夹
             */
            String path = Environment.getExternalStorageDirectory().getPath();
            File mediaStorageDir = new File(path + "/crafts_videos");
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.e("TAG", "文件夹创建失败");
                    return null;
                }
            }
            /**
             * 文件根据当前的毫秒数给自己命名
             */
            String timeStamp = String.valueOf(System.currentTimeMillis());
            timeStamp = timeStamp.substring(7);
            String imageFileName = "V" + timeStamp;
            String suffix = ".mp4";
            File mediaFile = new File(mediaStorageDir + File.separator + imageFileName + suffix);
            return mediaFile;
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info_more:
                startActivity(new Intent(getActivity(), EditInfoActivity.class));
                break;
            case R.id.my_info_feedback:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.my_info_sets:
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;
            case R.id.my_info_crafts_upload:
                startActivity(new Intent(getActivity(), MyRecordActivity.class));
                break;
        }
    }
/*
    private void showUserName() {
        sp = getActivity().getSharedPreferences("CraftsmanUserInfo.txt", Context.MODE_PRIVATE);
        if(sp.getString("nickName","").equals("")) {
            mMyInfoUserName.setText(userClass);
            return;
        }
        mMyInfoUserName.setText(sp.getString("nickName", ""));
    }*/
}
