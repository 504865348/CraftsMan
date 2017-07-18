package com.joshua.craftsman.activity.craftsHome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.ask.AskQuestionActivity;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.craftHome.CraftAlbumFragment;
import com.joshua.craftsman.fragment.craftHome.CraftQAFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CraftsHomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.crafts_name) TextView mCraftsName;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.crafts_image_icon) ImageView mCraftsImageIcon;
    @BindView(R.id.crafts_text_name) TextView mCraftsTextName;
    @BindView(R.id.crafts_text_introduction) TextView mCraftsTextIntroduction;
    @BindView(R.id.crafts_image_follow) ImageView mCraftsImageFollow;
    @BindView(R.id.crafts_image_ask) ImageView mCraftsImageAsk;
    @BindView(R.id.crafts_tv_album) TextView mCraftsTvAlbum;
    @BindView(R.id.crafts_ll_album) LinearLayout mCraftsLlAlbum;
    @BindView(R.id.crafts_tv_q_a) TextView mCraftsTvQA;
    @BindView(R.id.crafts_ll_q_a) LinearLayout mCraftsLlQA;
    @BindView(R.id.crafts_ll_switch) LinearLayout mCraftsLlSwitch;
    @BindView(R.id.img_tab_line) ImageView mTabLine;
    @BindView(R.id.crafts_view_pager) ViewPager mViewPager;

    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private PagerAdapter adapter;
    private int screenWidth;
    private CraftAlbumFragment mCraftAlbumFragment;
    private CraftQAFragment mCraftQAFragment;
    private String itemCraftsName;
    private String itemCraftsIntro;
    private String itemCraftsPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crafts_home);
        ButterKnife.bind(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        initPager();
        initTabLineWidth();
        initView();
        initListener();
    }
    /**
     * 初始化滑动 Pager 数据
     */
    private void initPager() {
        mCraftAlbumFragment = new CraftAlbumFragment();
        mCraftQAFragment = new CraftQAFragment();
        mFragmentList.add(mCraftAlbumFragment);
        mFragmentList.add(mCraftQAFragment);
        /**
         * 设置适配器和初始选中项
         */
        adapter = new com.joshua.craftsman.fragment.homepage
                .PagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mCraftsTvAlbum.setTextColor(Color.RED);
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
                lp.leftMargin = screenWidth/2*position + positionOffsetPixels/2;
                mTabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mCraftsTvAlbum.setTextColor(Color.RED);
                        break;
                    case 1:
                        mCraftsTvQA.setTextColor(Color.RED);
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
        mCraftsTvAlbum.setTextColor(Color.BLACK);
        mCraftsTvQA.setTextColor(Color.BLACK);
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
        itemCraftsName = getIntent().getStringExtra("craftsName");
        itemCraftsPic = getIntent().getStringExtra("craftsPic");
        mCraftsName.setText(itemCraftsName);
        mCraftsTextName.setText(itemCraftsName);
        Glide.with(this).load(itemCraftsPic).into(mCraftsImageIcon);

    }

    private void initListener() {
        mCraftsImageFollow.setOnClickListener(this);
        mCraftsImageAsk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.crafts_image_ask:
                Intent intent = new Intent(mBaseActivity, AskQuestionActivity.class);
                intent.putExtra("answer", itemCraftsName);
                mBaseActivity.startActivity(intent);
                break;
            case R.id.crafts_image_follow:
                getAttention();
                break;
        }
    }

    private void getAttention() {


        //Toast.makeText(mBaseActivity, "已关注", Toast.LENGTH_SHORT).show();
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