package com.joshua.craftsman.activity.record;

import android.os.Bundle;
import android.view.MenuItem;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class PlayerFrameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_frame);
        String url=getIntent().getStringExtra("url");
        startFullScreen(url);
    }



    public void startFullScreen(String url) {
        JCVideoPlayerStandard.startFullscreen(this,
                JCVideoPlayerStandard.class,
                url);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
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
