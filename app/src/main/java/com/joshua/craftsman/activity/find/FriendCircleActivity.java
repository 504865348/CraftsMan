package com.joshua.craftsman.activity.find;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendCircleActivity extends BaseActivity {


    @BindView(R.id.find_circle_tool_bar) Toolbar mFindCircleToolBar;
    @BindView(R.id.find_ll_find_friends) LinearLayout mFindLlFindFriends;
    @BindView(R.id.find_friend_circle_rv) RecyclerView mFindFriendCircleRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_content_circle);
        ButterKnife.bind(this);

        mFindCircleToolBar.setTitle("");
        setSupportActionBar(mFindCircleToolBar);
    }

    @OnClick(R.id.find_ll_find_friends)
    public void onViewClicked() {
        startActivity(new Intent(FriendCircleActivity.this, FindFriendsActivity.class));
    }

    /**
     * 监听返回按钮
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
