package com.joshua.craftsman.wang.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.joshua.craftsman.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 18012 on 2017/4/29.
 */

public class p13_2 extends AppCompatActivity {
    @BindView(R.id.next)
    ImageButton user;//用户跳转
    @BindView(R.id.orderform)
    ImageButton orderform;//订单跳转
    @BindView(R.id.money)
    ImageButton money;//匠币跳转
    @BindView(R.id.question)
    ImageButton question;//问答界面跳转\
    @BindView(R.id.opinion)
    ImageButton opinion;//意见反馈界面
    @BindView(R.id.set)
    ImageButton set;//设置跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info_common);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.next, R.id.orderform, R.id.money, R.id.question, R.id.opinion, R.id.set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                Intent intent=new Intent();
                intent.setClass(p13_2.this,p20.class);
                startActivity(intent);
                break;
            case R.id.orderform:

                break;
            case R.id.money:
                Intent intent2=new Intent();
                intent2.setClass(p13_2.this,p16.class);
                startActivity(intent2);
                break;
            case R.id.question:
                Intent intent3=new Intent();
                intent3.setClass(p13_2.this,p28_2.class);
                startActivity(intent3);
                break;
            case R.id.opinion:
                Intent intent4=new Intent();
                intent4.setClass(p13_2.this,p25.class);
                startActivity(intent4);
                break;
            case R.id.set:
                Intent intent5=new Intent();
                intent5.setClass(p13_2.this,p26.class);
                startActivity(intent5);
                break;
        }
    }
}
