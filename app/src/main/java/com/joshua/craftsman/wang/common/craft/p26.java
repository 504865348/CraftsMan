package com.joshua.craftsman.wang.common.craft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.joshua.craftsman.R;
import com.joshua.craftsman.wang.common.p40;
import com.joshua.craftsman.wang.common.p43;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 18012 on 2017/4/29.
 */

public class p26 extends AppCompatActivity {
    @BindView(R.id.count)
    ImageButton count;//账号绑定跳转
    @BindView(R.id.password)
    ImageButton password;//密码修改

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p26);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.count, R.id.password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.count:
                Intent intent=new Intent();
                intent.setClass(p26.this,p40.class);
                startActivity(intent);
                break;
            case R.id.password:
                Intent intent1=new Intent();
                intent1.setClass(p26.this,p43.class);
                startActivity(intent1);
                break;
        }
    }
}
