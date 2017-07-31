package com.joshua.craftsman.activity.my;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyBillboardActivity extends BaseActivity {

    @BindView(R.id.my_billboard_toolbar)
    Toolbar myBillboardToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_billboard);
        ButterKnife.bind(this);
        myBillboardToolbar.setTitle("");
        setSupportActionBar(myBillboardToolbar);
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
