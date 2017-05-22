package com.joshua.craftsman.wang.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.joshua.craftsman.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class common_set extends AppCompatActivity {
    @BindView(R.id.count)
    ImageButton count;//账号绑定跳转
    @BindView(R.id.password)
    ImageButton password;//密码修改

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.count, R.id.password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.count:
                Intent intent=new Intent();
                intent.setClass(common_set.this, common_count.class);
                startActivity(intent);
                break;
            case R.id.password:
                Intent intent1=new Intent();
                intent1.setClass(common_set.this,common_change.class);
                startActivity(intent1);
                break;
        }
    }
}
