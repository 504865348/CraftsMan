package com.joshua.craftsman.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.fragment.BillBoardFragment;
import com.joshua.craftsman.fragment.CraftsInfoFragment;
import com.joshua.craftsman.fragment.FindFragment;
import com.joshua.craftsman.fragment.HomeFragment;
import com.joshua.craftsman.fragment.PublicInfoFragment;
import com.joshua.craftsman.fragment.QAFragment;
import com.joshua.craftsman.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity{

    private int position = 0;
    private List<BaseFragment> mFragments;
    private BaseFragment tempFragment = null;
    private IntentFilter mFilter;
    private String intentAction;
    private ConnectivityManager mConnectivityManager;
    private NetworkInfo netInfo;
    private int netType;

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

        mFragments = new ArrayList<>();
        /**
         * 将主页面的 Fragment 添加到集合中
         */
        mFragments.add(new HomeFragment());
        mFragments.add(new BillBoardFragment());
        mFragments.add(new QAFragment());
        mFragments.add(new FindFragment());
        String type = PrefUtils.getString(mBaseActivity,"type","normal");
        if (type.equals("normal")) {
            mFragments.add(new PublicInfoFragment());
        } else {
            mFragments.add(new CraftsInfoFragment());
        }

        /**
         * 设置 RadioGroup 点击监听事件
         */
        setRadioGroupListener();

        mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myNetReceiver, mFilter);
    }

    /**
     * 监听网络连接是否正常
     */
    private BroadcastReceiver myNetReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            intentAction = intent.getAction();
            if (intentAction.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                mConnectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                netInfo = mConnectivityManager.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isAvailable()) {
                    netType = netInfo.getType();
                    if (netType == ConnectivityManager.TYPE_WIFI) {

                    } else if (netType == ConnectivityManager.TYPE_ETHERNET) {

                    } else if (netType == ConnectivityManager.TYPE_MOBILE) {

                    }
                } else {
                    Toast.makeText(MainActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("请检查网络连接");
                    dialog.setNegativeButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                    }
                });
                    dialog.show();
                }
            }
        }
    };

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
}
