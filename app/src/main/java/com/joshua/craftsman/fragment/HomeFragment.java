package com.joshua.craftsman.fragment;

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

public class HomeFragment extends BaseFragment {

    @BindView(R.id.main_text_search) TextView mTextSearch;
    @BindView(R.id.main_image_history) ImageView mImageHistory;
    @BindView(R.id.main_image_download) ImageView mImageDownload;
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
        Log.e("TAG", "Home-->initView()");
        view = View.inflate(mContext, R.layout.home_page, null);
        return view;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        initPager();
        initTabLineWidth();
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

        adapter = new com.joshua.craftsman.fragment.homepage
                .PagerAdapter(getFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);

        /**
         * 添加滑动适配器
         */
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * position :当前页面，及你点击滑动的页面
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
        mTvRecommend.setTextColor(0xff8c8c8c);
        mTvHot.setTextColor(0xff8c8c8c);
        mTvClassify.setTextColor(0xff8c8c8c);
        mTvCrafts.setTextColor(0xff8c8c8c);
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 4;
        mTabLine.setLayoutParams(lp);
    }

}
