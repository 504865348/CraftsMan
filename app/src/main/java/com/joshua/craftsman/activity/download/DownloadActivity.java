package com.joshua.craftsman.activity.download;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joshua.craftsman.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadActivity extends AppCompatActivity {

    @BindView(R.id.toolbar4) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        ButterKnife.bind(this);
        setView();
    }

    private void setView() {
        setSupportActionBar(mToolbar);
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
