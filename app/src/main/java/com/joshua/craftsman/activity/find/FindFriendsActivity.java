package com.joshua.craftsman.activity.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindFriendsActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.find_friends_back_img) ImageView findFriendsBackImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friends);
        ButterKnife.bind(this);
        initListener();
    }
    private void initListener() {
        findFriendsBackImg.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_friends_back_img:
                startActivity(new Intent(this, FriendCircleActivity.class));
                break;
        }
    }
}
