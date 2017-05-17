package com.joshua.craftsman.activity.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.joshua.craftsman.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.search_history_back) ImageView img_back;
    @BindView(R.id.search_interface_search) TextView text_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        ButterKnife.bind(this);
        setListener();
    }

    private void setListener() {
        img_back.setOnClickListener(this);
        text_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_history_back:
                finish();
                break;
            case R.id.search_interface_search:
                Intent intent = new Intent(this, SearchResultActivity.class);
                startActivity(intent);
                break;
        }
    }
}
