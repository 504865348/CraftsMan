package com.joshua.craftsman.activity.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.joshua.craftsman.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.search_history_back) ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_history);

        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_history_back:
                finish();
                break;
        }
    }
}
