package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.account.MoneyActivity;
import com.joshua.craftsman.activity.answer.MyAskAnswerActivity;
import com.joshua.craftsman.activity.record.MyRecordActivity;
import com.joshua.craftsman.wang.common.common_money;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.joshua.craftsman.R.id.my_info_my_coins;

public class CraftsInfoFragment extends BaseFragment {

    private View view;
    private Uri fileUri;
    private int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 1;

    @BindView(R.id.my_info_crafts_record)
    RelativeLayout btn_record;
    @BindView(R.id.my_info_my_records)
    LinearLayout btn_my_record;
    @BindView(R.id.my_info_my_q_a)
    LinearLayout my_info_my_q_a;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.my_info_crafts, null);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
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


}
