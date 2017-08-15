package com.joshua.craftsman.activity.albumHome;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.account.LoginActivity;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.albumHome.AlbumDetailsFragment;
import com.joshua.craftsman.fragment.albumHome.AlbumProgramFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumHomeActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.album_detail_pager)
    ViewPager mViewPager;
    @BindView(R.id.album_detail_tv_particulars)
    TextView albumDetailTvParticulars;
    @BindView(R.id.album_detail_tv_program)
    TextView albumDetailTvProgram;
    @BindView(R.id.img_tab_line)
    ImageView mTabLine;
    @BindView(R.id.album_subscribe)
    Button albumSubscribe;
    @BindView(R.id.album_buy)
    Button albumBuy;
    @BindView(R.id.album_detail_model)
    TextView albumDetailModel;
    @BindView(R.id.album_share)
    ImageView albumShare;

    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private PagerAdapter adapter;
    private int screenWidth;
    private AlbumDetailsFragment mAlbumDetailsFragment;
    private AlbumProgramFragment mAlbumProgramFragment;

    private String itemAlbumId, itemAlbumName, itemAlbumPic, itemAlbumCrafts,
            itemAlbumModel, itemAlbumClassify, getItemAlbumPlay, itemAlbumSubscribe;
    public static String homeAlbumCraftName = "";
    public static String homeAlbumId = "";
    public static String homeAlbumIntroduction = "";
    public static String albumPic = "";
    private boolean isSubscribe = true;
    private SharedPreferences sp;
    private String subscribeFlag, subscribeUser;
    private String userName = LoginActivity.appUserName;


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
        initListener();
        //showInfo();
    }

    private void initPager() {
        homeAlbumCraftName = getIntent().getStringExtra("albumCrafts");
        homeAlbumId = getIntent().getStringExtra("albumId");
        homeAlbumIntroduction = getIntent().getStringExtra("albumIntroduction");
        mAlbumDetailsFragment = new AlbumDetailsFragment();
        mAlbumProgramFragment = new AlbumProgramFragment();
        mFragmentList.add(mAlbumDetailsFragment);
        mFragmentList.add(mAlbumProgramFragment);
        adapter = new com.joshua.craftsman.fragment.homepage
                .PagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        albumDetailTvParticulars.setTextColor(Color.RED);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void resetTextView() {
        albumDetailTvParticulars.setTextColor(Color.BLACK);
        albumDetailTvProgram.setTextColor(Color.BLACK);
    }

    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 2;
        mTabLine.setLayoutParams(lp);
    }

    private void initView() {
        itemAlbumId = getIntent().getStringExtra("albumId");
        itemAlbumName = getIntent().getStringExtra("albumName");
        itemAlbumPic = getIntent().getStringExtra("albumPic");
        itemAlbumCrafts = getIntent().getStringExtra("albumCrafts");
        itemAlbumClassify = getIntent().getStringExtra("albumClassify");
        itemAlbumModel = getIntent().getStringExtra("albumModel");
        getItemAlbumPlay = getIntent().getStringExtra("albumPlay");
        itemAlbumSubscribe = getIntent().getStringExtra("albumSubscribe");
        albumDetailName.setText(itemAlbumName);
        albumDetailCraftsName.setText(itemAlbumCrafts);
        albumDetailClassification.setText(itemAlbumClassify);
        albumDetailModel.setText(itemAlbumModel);
        albumDetailPlayNum.setText(getItemAlbumPlay);
        Glide.with(this).load(itemAlbumPic).into(albumDetailCover);
        albumPic = itemAlbumPic;
    }

    private void initListener() {
        albumShare.setOnClickListener(this);
        albumSubscribe.setOnClickListener(this);
        albumBuy.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.album_share:
                Toast.makeText(mBaseActivity, "暂未开放专辑分享功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.album_subscribe:
                //saveInfo(isSubscribe);
                //albumIsSubscribe(String.valueOf(isSubscribe));
                //isSubscribe = !isSubscribe;
                Toast.makeText(mBaseActivity, "暂未开放专辑订阅功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.album_buy:
                Toast.makeText(mBaseActivity, "暂未开放专辑购买功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }
/*
    private void albumIsSubscribe(String strIsSubscribe) {
        if (strIsSubscribe.equals("true")) {
            if (itemAlbumSubscribe.equals("null")) {
                albumSubscribe.setText("已订阅(" + 0 + ")");
            }
            else {
                albumSubscribe.setText("已订阅(" + itemAlbumSubscribe + ")");
            }
            putDataToServer(strIsSubscribe);
        } else {
            if (itemAlbumSubscribe.equals("null")) {
                albumSubscribe.setText("订阅(" + 0 + ")");
            }
            else {
                albumSubscribe.setText("订阅(" + itemAlbumSubscribe + ")");
            }
            putDataToServer(strIsSubscribe);
        }
    }

    private void saveInfo(boolean bool) {
        sp = getSharedPreferences("SubscribeAlbumId" + homeAlbumId, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UserName", userName);
        editor.putString("AlbumId", itemAlbumId);
        editor.putString("IsSubscribe", String.valueOf(bool));
        editor.commit();
    }


    private void showInfo() {
        sp = getSharedPreferences("SubscribeAlbumId" + homeAlbumId, Context.MODE_PRIVATE);
        subscribeFlag = sp.getString("IsSubscribe", "");
        subscribeUser = sp.getString("UserName", "");
        if (!subscribeUser.equals(userName)) {
            deleteSharedPreferencesFile();
        } else {
            if (subscribeFlag.equals(String.valueOf(true))) {
                albumSubscribe.setText("已订阅(" + itemAlbumSubscribe + ")");
            } else {
                albumSubscribe.setText("订阅(" + itemAlbumSubscribe + ")");
            }
        }
    }

    private void deleteSharedPreferencesFile() {
        File file = new File("/data/data/" + getPackageName().toString() + "/shared_prefs", "SubscribeAlbumId" + homeAlbumId + ".xml");
        if (file.exists()) {
            file.delete();
        }
    }


    private void putDataToServer(String strFlag) {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.ALBUM_SUBSCRIBE)
                .add("albumId", itemAlbumId)
                .add("flag", strFlag)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                Log.d(TAG, "success: " + result);
            }

            @Override
            protected void error() {
            }
        });
    }
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
