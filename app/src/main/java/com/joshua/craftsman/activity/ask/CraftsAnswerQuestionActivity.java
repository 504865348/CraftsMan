package com.joshua.craftsman.activity.ask;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.utils.AudioRecoderUtils;
import com.joshua.craftsman.utils.PopupWindowFactory;
import com.joshua.craftsman.utils.TimeUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.joshua.craftsman.R.id.answer_question_recording;

public class CraftsAnswerQuestionActivity extends BaseActivity {
    static final int VOICE_REQUEST_CODE = 66;

    private ImageView mImageView;
    private TextView mTextView;
    private AudioRecoderUtils mAudioRecoderUtils;
    private Context context;
    private PopupWindowFactory mPop;
    private LinearLayout ll;

    @BindView(R.id.answer_question_recording)
    Button mButton;

    private String mFilePath;
    private static MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_question_44);
        ButterKnife.bind(this);
        initData();
        final View view = View.inflate(this, R.layout.layout_microphone, null);
        mPop = new PopupWindowFactory(this, view);
        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) view.findViewById(R.id.tv_recording_time);
        context = this;
        ll = (LinearLayout) findViewById(R.id.ll);
        mAudioRecoderUtils = new AudioRecoderUtils();

        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(TimeUtils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                Toast.makeText(mBaseActivity, "录音保存在：" + filePath, Toast.LENGTH_SHORT).show();
                mTextView.setText(TimeUtils.long2String(0));
            }
        });
        StartListener();

        //6.0以上需要权限申请
//        requestPermissions();
    }

    private void initData() {
        String question = getIntent().getStringExtra("question");
    }


    public void StartListener() {
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPop.showAtLocation(ll, Gravity.CENTER, 0, 0);
                        mButton.setText("松开保存");
                        mAudioRecoderUtils.startRecord();
                        mFilePath = mAudioRecoderUtils.getFilePath();
                        break;

                    case MotionEvent.ACTION_UP:
                        mAudioRecoderUtils.stopRecord();        //结束录音（保存录音文件）
//                        mAudioRecoderUtils.cancelRecord();    //取消录音（不保存录音文件）
                        mPop.dismiss();
                        mButton.setText("按住说话");

                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.answer_question_listen)
    public void listener() {
        music = new MediaPlayer();
        try {
            music.setDataSource(mFilePath);
            music.prepare();
            music.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    /**
//     * 开启扫描之前判断权限是否打开
//     */
//    private void requestPermissions() {
//        //判断是否开启摄像头权限
//        if ((ContextCompat.checkSelfPermission(context,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
//                (ContextCompat.checkSelfPermission(context,
//                        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
//                ) {
//            StartListener();
//
//            //判断是否开启语音权限
//        } else {
//            //请求获取摄像头权限
//            ActivityCompat.requestPermissions((Activity) context,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, VOICE_REQUEST_CODE);
//        }
//
//    }

//    /**
//     * 请求权限回调
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == VOICE_REQUEST_CODE) {
//            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
//                StartListener();
//            } else {
//                Toast.makeText(context, "已拒绝权限！", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
