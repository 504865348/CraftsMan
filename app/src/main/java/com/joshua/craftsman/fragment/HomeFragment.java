package com.joshua.craftsman.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.download.DownloadActivity;
import com.joshua.craftsman.activity.search.SearchActivity;
import com.joshua.craftsman.activity.search.SearchHistoryActivity;
import com.joshua.craftsman.fragment.homepage.HomeClassifyPager;
import com.joshua.craftsman.fragment.homepage.HomeCraftsPager;
import com.joshua.craftsman.fragment.homepage.HomeHotPager;
import com.joshua.craftsman.fragment.homepage.HomeRecommendPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 陈天然 on 2017-05-15.
 * 首页 Fragment
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.main_text_search) TextView mTextSearch;
    @BindView(R.id.main_ll_history) LinearLayout mLinearHistory;
    @BindView(R.id.main_ll_download) LinearLayout mLinearDownload;
    /**
     * Pager 相关的视图对象
     */
    @BindView(R.id.home_tv_recommend) TextView mTvRecommend;
    @BindView(R.id.home_tv_hot) TextView mTvHot;
    @BindView(R.id.home_tv_classify) TextView mTvClassify;
    @BindView(R.id.home_tv_crafts) TextView mTvCrafts;
    @BindView(R.id.home_page_pager) ViewPager mViewPager;
    @BindView(R.id.home_tab_line) ImageView mTabLine;
    /**
     * 私有成员变量
     */
    private View view;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private HomeRecommendPager recommendPager;
    private HomeHotPager hotPager;
    private HomeClassifyPager classifyPager;
    private HomeCraftsPager craftsPager;
    private PagerAdapter adapter;
    private int screenWidth;

    /**
     * 初始化视图
     */
    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.home_page, null);
        return view;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        /**
         * 点击事件
         */
        mTextSearch.setOnClickListener(this);
        mLinearHistory.setOnClickListener(this);
        mLinearDownload.setOnClickListener(this);
        /**
         * 滑动页面设置
         */
        initPager();
        initTabLineWidth();
        mTvRecommend.setOnClickListener(this);
        mTvHot.setOnClickListener(this);
        mTvClassify.setOnClickListener(this);
        mTvCrafts.setOnClickListener(this);
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
        recommendPager = new HomeRecommendPager();
        hotPager = new HomeHotPager();
        classifyPager = new HomeClassifyPager();
        craftsPager = new HomeCraftsPager();
        mFragmentList.add(recommendPager);
        mFragmentList.add(hotPager);
        mFragmentList.add(classifyPager);
        mFragmentList.add(craftsPager);
        /**
         * 设置适配器和初始选中项
         */
        adapter = new com.joshua.craftsman.fragment.homepage
                .PagerAdapter(getFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mTvRecommend.setTextColor(Color.RED);
        /**
         * 添加滑动适配器
         */
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * position :当前页面，及点击滑动的页面
             * offset:当前页面偏移的百分比
             * positionOffsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
                lp.leftMargin = screenWidth/4*position + positionOffsetPixels/4;
                mTabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTvRecommend.setTextColor(Color.RED);
                        break;
                    case 1:
                        mTvHot.setTextColor(Color.RED);
                        break;
                    case 2:
                        mTvClassify.setTextColor(Color.RED);
                        break;
                    case 3:
                        mTvCrafts.setTextColor(Color.RED);
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
        mTvRecommend.setTextColor(Color.BLACK);
        mTvHot.setTextColor(Color.BLACK);
        mTvClassify.setTextColor(Color.BLACK);
        mTvCrafts.setTextColor(Color.BLACK);
    }

    /**
     * 设置滑动条的宽度为屏幕的 1/4 (根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 4;
        mTabLine.setLayoutParams(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_tv_recommend:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.home_tv_hot:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.home_tv_classify:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.home_tv_crafts:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.main_text_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.main_ll_history:
                startActivity(new Intent(getActivity(), SearchHistoryActivity.class));
                break;
            case R.id.main_ll_download:
                startActivity(new Intent(getActivity(), DownloadActivity.class));
                break;
        }
    }
}
