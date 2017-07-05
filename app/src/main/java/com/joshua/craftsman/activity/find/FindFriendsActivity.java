package com.joshua.craftsman.activity.find;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.findfriendpage.AttentionPager;
import com.joshua.craftsman.fragment.findfriendpage.FriendPager;
import com.joshua.craftsman.fragment.homepage.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindFriendsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.recommend_attention)
    TextView mRecommendAttention;
    @BindView(R.id.my_friend)
    TextView mMyFriend;
    @BindView(R.id.find_page_switch_tab)
    LinearLayout mFindPageSwitchTab;
    @BindView(R.id.find_friend_tab_line)
    ImageView mTabLineIv;
    @BindView(R.id.find_friends_page_pager)
    ViewPager mViewPager;
    @BindView(R.id.find_friend_tool_bar)
    Toolbar findFriendToolBar;

    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private AttentionPager mAttentionPager;
    private FriendPager mFriendPager;
    private PagerAdapter mPagerAdapter;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friends);
        ButterKnife.bind(this);
        initPager();
        initTabLineWidth();
        initListener();
    }

    private void initPager() {
        mAttentionPager = new AttentionPager();
        mFriendPager = new FriendPager();
        mFragmentList.add(mAttentionPager);
        mFragmentList.add(mFriendPager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(0);
        mRecommendAttention.setTextColor(Color.RED);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
                lp.leftMargin = screenWidth / 2 * position + positionOffsetPixels / 2;
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextColor();
                switch (position) {
                    case 0:
                        mRecommendAttention.setTextColor(Color.RED);
                        break;
                    case 1:
                        mMyFriend.setTextColor(Color.RED);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void resetTextColor() {
        mRecommendAttention.setTextColor(Color.BLACK);
        mMyFriend.setTextColor(Color.BLACK);
    }

    /**
     * 设置滑动条的宽度为屏幕的 1/2 (根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLineIv.setLayoutParams(lp);
    }

    private void initListener() {
        findFriendToolBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_friend_tool_bar:
                startActivity(new Intent(mBaseActivity, FriendCircleActivity.class));
                break;
        }
    }
}
