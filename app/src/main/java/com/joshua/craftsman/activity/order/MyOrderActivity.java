package com.joshua.craftsman.activity.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.myorder.MyOrderAlbumFragment;
import com.joshua.craftsman.fragment.myorder.MyOrderProgramFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.myrecorder_toolbar) Toolbar mToolbar;
    @BindView(R.id.my_order_ll_album) LinearLayout mMyOrderLlAlbum;
    @BindView(R.id.my_order_ll_program) LinearLayout mMyOrderLlProgram;
    @BindView(R.id.home_tab_line) ImageView mTabLine;
    @BindView(R.id.my_order_page_pager) ViewPager mViewPager;
    @BindView(R.id.my_order_tv_album) TextView mMyOrderTvAlbum;
    @BindView(R.id.my_order_tv_program) TextView mMyOrderTvProgram;

    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private PagerAdapter adapter;
    private int screenWidth;
    private MyOrderAlbumFragment mMyOrderAlbumFragment;
    private MyOrderProgramFragment mMyOrderProgramFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order);
        ButterKnife.bind(this);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        initPager();
        initTabLineWidth();
    }


    /**
     * 初始化滑动 Pager 数据
     */
    private void initPager() {
        mMyOrderAlbumFragment = new MyOrderAlbumFragment();
        mMyOrderProgramFragment = new MyOrderProgramFragment();
        mFragmentList.add(mMyOrderAlbumFragment);
        mFragmentList.add(mMyOrderProgramFragment);
        /**
         * 设置适配器和初始选中项
         */
        adapter = new com.joshua.craftsman.fragment.homepage
                .PagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mMyOrderTvAlbum.setTextColor(Color.RED);
        /**
         * 添加滑动监听器
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
                lp.leftMargin = screenWidth / 2 * position + positionOffsetPixels / 2;
                mTabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mMyOrderTvAlbum.setTextColor(Color.RED);
                        break;
                    case 1:
                        mMyOrderTvProgram.setTextColor(Color.RED);
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
        mMyOrderTvAlbum.setTextColor(Color.BLACK);
        mMyOrderTvProgram.setTextColor(Color.BLACK);
    }

    /**
     * 设置滑动条的宽度为屏幕的 1/2 (根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLine.setLayoutParams(lp);
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
