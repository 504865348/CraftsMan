package com.joshua.craftsman.activity.albumHome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.activity.account.LoginActivity;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.activity.other.ProgrameFragment;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.entity.joshua.OrderType;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.albumHome.AlbumDetailsFragment;
import com.joshua.craftsman.fragment.albumHome.AlbumProgramFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.pay.event.MessageEvent;
import com.joshua.craftsman.pay.global.PayAction;
import com.joshua.craftsman.pay.global.PopWindowUtils;
import com.joshua.craftsman.pay.util.PaySuccess;
import com.joshua.craftsman.pay.util.PayUtils;
import com.joshua.craftsman.utils.AudioRecoderUtils;
import com.joshua.craftsman.utils.PrefUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.joshua.craftsman.R.id.btn_collect;
import static com.joshua.craftsman.R.id.et_question;

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
    @BindView(R.id.album_detail_ll_particulars)
    LinearLayout album_detail_ll_particulars;
    @BindView(R.id.album_detail_ll_program)
    LinearLayout album_detail_ll_program;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.ll_price)
    LinearLayout ll_price;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;


    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private PagerAdapter adapter;
    private int screenWidth;
    private AlbumDetailsFragment mAlbumDetailsFragment;
    private ProgrameFragment mAlbumProgramFragment;

    private String itemAlbumId, itemAlbumName, itemAlbumPic, itemAlbumCrafts,
            itemAlbumModel, itemAlbumClassify, getItemAlbumPlay, itemAlbumSubscribe;
    public static String homeAlbumCraftName = "";
    public static String homeAlbumId = "";
    public static String homeAlbumIntroduction = "";
    public static String albumPic = "";
    private boolean isSubscribe = true;
    private SharedPreferences sp;
    private String subscribeFlag, subscribeUser, isFocus;
    private String userName = LoginActivity.appUserName;
    private OkHttpClient mClient;
    public String homeAlbumPrice = "";


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
        EventBus.getDefault().register(this);
        //showInfo();

    }

    /**
     * 查看是否被购买
     */
    private void isBuy() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.ALBUM_IS_BUY)
                .add("albumId", homeAlbumId)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(mBaseActivity) {
            @Override
            protected void success(final String result) {
                Log.d("jiage", "run: "+result);
                if (result.equals("true")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ll_price.setVisibility(View.GONE);
                            albumBuy.setText("已购买");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ll_price.setVisibility(View.VISIBLE);
                            homeAlbumPrice=result;
                            albumBuy.setText("购买");
                            price.setText(result);
                        }
                    });
                }
            }

            @Override
            protected void error() {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initPager() {
        homeAlbumCraftName = getIntent().getStringExtra("albumCrafts");
        homeAlbumId = getIntent().getStringExtra("albumId");
        homeAlbumIntroduction = getIntent().getStringExtra("albumIntroduction");

        mAlbumDetailsFragment = new AlbumDetailsFragment();
        mAlbumProgramFragment = new ProgrameFragment();
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

        isBuy();
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
        isFocus = PrefUtils.getString(this, itemAlbumId, "0");
        if (isFocus.equals("1")) {
            albumSubscribe.setText("取消订阅");
        }
        albumDetailName.setText(itemAlbumName);
        albumDetailCraftsName.setText(itemAlbumCrafts);
        albumDetailClassification.setText(itemAlbumClassify);
        albumDetailModel.setText(itemAlbumModel);
//        albumDetailPlayNum.setText(getItemAlbumPlay);
        Glide.with(this).load(itemAlbumPic).into(albumDetailCover);
        albumPic = itemAlbumPic;
    }

    private void initListener() {
        albumShare.setOnClickListener(this);
        albumSubscribe.setOnClickListener(this);
        albumBuy.setOnClickListener(this);
        album_detail_ll_particulars.setOnClickListener(this);
        album_detail_ll_program.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.album_share:
                Toast.makeText(mBaseActivity, "暂未开放专辑分享功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.album_subscribe:
                subscribe(isFocus);
                break;
            case R.id.album_buy:
                if (albumBuy.getText().equals("已购买")) {
                    Toast.makeText(mBaseActivity, "已购买该专辑", Toast.LENGTH_SHORT).show();
                } else {
                    buyAlbum();
                }
                break;
            case R.id.album_detail_ll_particulars:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.album_detail_ll_program:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    /**
     * 购买专辑
     */
    private void buyAlbum() {
        PopWindowUtils.showPop(this, ll_content, new PayAction() {
            @Override
            public void aliPay() {
                PayUtils utils = new PayUtils(mBaseActivity);
                utils.setPaySuccess(new PaySuccess() {
                    @Override
                    public void onSuccess(final String orderNo) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAlbumProgramFragment.initData();
                                Toast.makeText(mBaseActivity,"专辑购买成功",Toast.LENGTH_SHORT).show();
                                albumBuy.setText("已购买");
                            }
                        });

                    }
                });
                utils.payV2(OrderType.TYPE_BYE_ALBUM, homeAlbumId, Float.parseFloat(homeAlbumPrice));
            }

            @Override
            public void wxPay() {
                PayUtils utils = new PayUtils(mBaseActivity);
                utils.payWx(OrderType.TYPE_BYE_ALBUM, homeAlbumId, Float.parseFloat(homeAlbumPrice));
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        mAlbumProgramFragment.initData();
        Toast.makeText(mBaseActivity,"专辑购买成功",Toast.LENGTH_SHORT).show();
        albumBuy.setText("已购买");
    }

    private void changeFocus() {
        if (isFocus.equals("1")) {
            albumSubscribe.setText("订阅");
            isFocus = "0";
        } else {
            albumSubscribe.setText("取消订阅");
            isFocus = "1";
        }
        PrefUtils.setString(this, itemAlbumId, isFocus);
    }

    /**
     * 订阅
     */
    private void subscribe(String type) {
        type = type.equals("1") ? "0" : "1";
        mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(mBaseActivity))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_SUBSCRIBE)
                .add("albumId", homeAlbumId)
                .add("flag", type)//    订阅/取消订阅 值 1/0
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(mBaseActivity) {
            @Override
            protected void success(String result) {
                if (result.equals("true")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mBaseActivity, "操作成功", Toast.LENGTH_SHORT).show();
                            changeFocus();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mBaseActivity, "操作失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            protected void error() {

            }
        });

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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
