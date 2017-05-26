package com.joshua.craftsman.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.entity.CarouselPic;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.BillBoardFragment;
import com.joshua.craftsman.fragment.CraftsInfoFragment;
import com.joshua.craftsman.fragment.FindFragment;
import com.joshua.craftsman.fragment.HomeFragment;
import com.joshua.craftsman.fragment.QAFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends BaseActivity {

    private int position = 0;
    private List<BaseFragment> mFragments;
    private BaseFragment tempFragment = null;
    private List<CarouselPic> list_pic;

    /**
     * 初始化视图对象
     */
    @BindView(R.id.main_frame_content)
    FrameLayout mMainFragment;
    @BindView(R.id.main_rg_guide)
    RadioGroup mMainRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getDataFromServer();
        list_pic = new ArrayList<>();
        /**
         * 将主页面的 Fragment 添加到集合中
         */
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new BillBoardFragment());
        mFragments.add(new QAFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new CraftsInfoFragment());
        /**
         * 设置 RadioGroup 点击监听事件
         */
        setRadioGroupListener();
    }

    /**
     * 导航栏的点击监听事件
     */
    private void setRadioGroupListener() {
        mMainRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_home_page:
                        position = 0;
                        break;
                    case R.id.main_rb_billboard:
                        position = 1;
                        break;
                    case R.id.main_rb_question:
                        position = 2;
                        break;
                    case R.id.main_rb_discover:
                        position = 3;
                        break;
                    case R.id.main_rb_my_info:
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment fragment = getFragment(position);
                switchFragment(fragment);
            }
        });
        mMainRadioGroup.check(R.id.main_rb_home_page);
    }

    /**
     * 根据位置得到对应 Fragment
     */
    private BaseFragment getFragment(int position) {
        if (mFragments != null && mFragments.size() > 0) {
            return mFragments.get(position);
        }
        return null;
    }

    /**
     * 切换 Fragment 的方法
     */
    private void switchFragment(BaseFragment nextFragment) {

        if (tempFragment != nextFragment) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            /**
             * 如果该 Fragment 未被添加，则将其添加
             * 如果已经添加，则直接显示
             */
            if (!nextFragment.isAdded()) {
                if (tempFragment != null) {
                    transaction.hide(tempFragment);
                }
                transaction.add(R.id.main_frame_content, nextFragment).commit();
            } else {
                if (tempFragment != null) {
                    transaction.hide(tempFragment);
                }
                transaction.show(nextFragment).commit();
            }
            /**
             * 将当前要显示的 Fragment 保存为缓存
             */
            tempFragment = nextFragment;
        }
    }


    private void getDataFromServer() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.CAROUSEL_PIC)
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
                parseData(result);

            }

            @Override
            protected void error() {

            }
        });
    }


    /**
     * 解析JSON数据
     *
     * @param result
     */
    private void parseData(String result) {
        Gson gson = new Gson();
        list_pic = gson.fromJson(result, new TypeToken<List<CarouselPic>>() {
        }.getType());

    }
}
