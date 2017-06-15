package com.joshua.craftsman.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.fragment.findfriendpage.AttentionPager;
import com.joshua.craftsman.fragment.findfriendpage.FriendPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindFriendsFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.recommend_attention) TextView tvAttention;
    @BindView(R.id.my_friend) TextView tvFriend;
    @BindView(R.id.find_friends_page_pager) ViewPager myViewPager;
    @BindView(R.id.find_friend_tab_line) ImageView myTabLine;

    private View view;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private AttentionPager attentionPager;
    private FriendPager friendPager;
    private PagerAdapter adapter;
    private int screenWidth;


    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.find_friends, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initPager();
        initTabLineWidth();
        tvAttention.setOnClickListener(this);
        tvFriend.setOnClickListener(this);
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

    /**
     * 初始化滑动 Pager 数据
     */
    private void initPager() {
        attentionPager = new AttentionPager();
        friendPager = new FriendPager();
        mFragmentList.add(attentionPager);
        mFragmentList.add(friendPager);
        /**
         * 设置适配器和初始选中项
         */
        adapter = new com.joshua.craftsman.fragment.homepage
                .PagerAdapter(getFragmentManager(), mFragmentList);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);
        tvAttention.setTextColor(Color.RED);
        /**
         * 添加滑动适配器
         */
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * position :当前页面，及点击滑动的页面
             * offset:当前页面偏移的百分比
             * positionOffsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) myTabLine.getLayoutParams();
                lp.leftMargin = screenWidth/2*position + positionOffsetPixels/2;
                myTabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        tvAttention.setTextColor(Color.RED);
                        break;
                    case 1:
                        tvFriend.setTextColor(Color.RED);
                        break;
                }
            }
            /**
             * 滑动中的状态 1: 正在滑动  2: 滑动完毕  3: 无操作
             */
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void resetTextView() {
        tvAttention.setTextColor(Color.BLACK);
        tvFriend.setTextColor(Color.BLACK);
    }

    /**
     * 设置滑动条的宽度为屏幕的 1/2 (根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) myTabLine.getLayoutParams();
        lp.width = screenWidth / 2;
        myTabLine.setLayoutParams(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recommend_attention:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.my_friend:
                myViewPager.setCurrentItem(1);
                break;
        }
    }
}
