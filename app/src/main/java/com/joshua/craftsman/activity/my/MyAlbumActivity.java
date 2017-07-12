package com.joshua.craftsman.activity.my;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAlbumActivity extends BaseActivity {

    @BindView(R.id.myAlbum_toolbar)
    Toolbar myAlbumToolbar;
    @BindView(R.id.myAlbum_rv)
    RecyclerView myAlbumRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_album);
        ButterKnife.bind(this);
        myAlbumToolbar.setTitle("");
        setSupportActionBar(myAlbumToolbar);
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
