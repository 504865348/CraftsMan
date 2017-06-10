package com.joshua.craftsman.activity.find;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendCircleActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.find_friends_next) ImageView findFriendsNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_content_circle);
        ButterKnife.bind(this);
        initListener();
    }
    private void initListener() {
        findFriendsNext.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_friends_next:
                startActivity(new Intent(this, FindFriendsActivity.class));
                break;
        }
    }
}
