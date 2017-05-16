package com.joshua.craftsman.fragment.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joshua.craftsman.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈天然 on 2017-05-16.
 * 首页滑动的 Pager 的适配器
 */

public class PagerAdapter extends FragmentPagerAdapter {

    List<BaseFragment> listFragment = new ArrayList<>();

    public PagerAdapter(FragmentManager fm, List<BaseFragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

}
