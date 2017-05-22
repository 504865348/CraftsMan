package com.joshua.craftsman.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import butterknife.ButterKnife;

public class HotActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_page_hot);
        ButterKnife.bind(this);
        putDatas();
        mRecyclerView = (RecyclerView) findViewById(R.id.hot_crafts_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new GalleryAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter = new GalleryAdapter(this, mDatas);
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(HotActivity.this, position+"", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
    private void putDatas() {
        //mDatas = new ArrayList<Integer>(Arrays.asList( ));
    }
}
