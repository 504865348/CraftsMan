package com.joshua.craftsman.activity.albumHome;

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

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.albumHome.AlbumDetailsFragment;
import com.joshua.craftsman.fragment.albumHome.AlbumProgramFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumHomeActivity extends BaseActivity {

    @BindView(R.id.album_detail_tool_bar)
    Toolbar albumDetailToolBar;
    @BindView(R.id.album_detail_cover)
    ImageView albumDetailCover;
    @BindView(R.id.album_detail_name)
    TextView albumDetailName;
    @BindView(R.id.album_detail_crafts_name)
    TextView albumDetailCraftsName;
    @BindView(R.id.album_detail_play_num)
    TextView albumDetailPlayNum;
    @BindView(R.id.album_detail_classification)
    TextView albumDetailClassification;
    @BindView(R.id.album_detail_subscribe)
    ImageView albumDetailSubscribe;
    @BindView(R.id.album_detail_buy)
    ImageView albumDetailBuy;
    @BindView(R.id.album_detail_pager)
    ViewPager mViewPager;
    @BindView(R.id.album_detail_tv_particulars)
    TextView albumDetailTvParticulars;
    @BindView(R.id.album_detail_tv_program)
    TextView albumDetailTvProgram;
    @BindView(R.id.img_tab_line)
    ImageView mTabLine;

    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private PagerAdapter adapter;
    private int screenWidth;
    private AlbumDetailsFragment mAlbumDetailsFragment;
    private AlbumProgramFragment mAlbumProgramFragment;

    private String itemAlbumName;
    private String itemAlbumPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_home);
        ButterKnife.bind(this);
        albumDetailToolBar.setTitle("");
        setSupportActionBar(albumDetailToolBar);
        initPager();
        initTabLineWidth();
        initView();
    }

    /**
     * 初始化滑动 Pager 数据
     */
    private void initPager() {
        mAlbumDetailsFragment = new AlbumDetailsFragment();
        mAlbumProgramFragment = new AlbumProgramFragment();
        mFragmentList.add(mAlbumDetailsFragment);
        mFragmentList.add(mAlbumProgramFragment);
        /**
         * 设置适配器和初始选中项
         */
        adapter = new com.joshua.craftsman.fragment.homepage
                .PagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        albumDetailTvParticulars.setTextColor(Color.RED);
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
                        albumDetailTvParticulars.setTextColor(Color.RED);
                        break;
                    case 1:
                        albumDetailTvProgram.setTextColor(Color.RED);
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
        albumDetailTvParticulars.setTextColor(Color.BLACK);
        albumDetailTvProgram.setTextColor(Color.BLACK);
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

    private void initView() {
        itemAlbumName = getIntent().getStringExtra("albumName");
        itemAlbumPic = getIntent().getStringExtra("albumPic");
        albumDetailName.setText(itemAlbumName);
        Glide.with(this).load(itemAlbumPic).into(albumDetailCover);
    }

    /**
     * 监听返回按键
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
