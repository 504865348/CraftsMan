package com.joshua.craftsman.activity.record;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.entity.joshua.VideoDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class PlayerFrameActivity extends BaseActivity {

    JCVideoPlayerStandard mJcVideoPlayerStandard;
    @BindView(R.id.video_player_album_cover)
    ImageView video_player_album_cover;
    @BindView(R.id. video_player_album_name)
    TextView  video_player_album_name;
    @BindView(R.id. video_player_album_subname)
    TextView  video_player_album_subname;
    @BindView(R.id. video_player_times)
    TextView  video_player_times;

    private VideoDetail mVideoDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_frame);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        mVideoDetail = (VideoDetail) getIntent().getSerializableExtra("entity");
        //沉浸式
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp(url
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        Glide.with(this)
                .load("http://139.224.35.126:8080/GJ/upload/background.png")
                .into(mJcVideoPlayerStandard.thumbImageView);
        initView();
    }

    private void initView() {
        Glide.with(this)
                .load(mVideoDetail.getAlbumImage())
                .into(video_player_album_cover);
        video_player_album_name.setText(mVideoDetail.getTitle());
        video_player_album_subname.setText(mVideoDetail.getClassifyName());
        video_player_times.setText(mVideoDetail.getPlayTimes());
    }


    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
