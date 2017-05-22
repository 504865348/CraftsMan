package com.joshua.craftsman.wang.common.craft;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.joshua.craftsman.R;
import com.joshua.craftsman.wang.common.common_craft;
import com.joshua.craftsman.wang.common.common_money;
import com.joshua.craftsman.wang.common.common_myquestion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class mine extends AppCompatActivity {
    @BindView(R.id.next)
    ImageButton user;//用户跳转
    @BindView(R.id.orderform)
    ImageButton orderform;//订单跳转
    @BindView(R.id.money)
    ImageButton money;//匠币跳转
    @BindView(R.id.question)
    ImageButton question;//问答界面跳转\
    @BindView(R.id.album)
    ImageButton album;
    @BindView(R.id.opinion)
    ImageButton opinion;//意见反馈界面
    @BindView(R.id.set)
    ImageButton set;//设置跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info_crafts);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.next,R.id.orderform, R.id.money, R.id.question, R.id.album, R.id.list, R.id.recorder, R.id.opinion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                tiaozhuan(mine.this,craft.class);

                break;
            case R.id.orderform:
                break;
            case R.id.money:
                tiaozhuan(mine.this,common_money.class);

                break;
            case R.id.question:
                tiaozhuan(mine.this,common_myquestion.class);

                break;
            case R.id.album:
                tiaozhuan(mine.this,album.class);

                break;
            case R.id.list:
                break;
            case R.id.recorder:
                break;
            case R.id.opinion:
                tiaozhuan(mine.this,question.class);
                break;
        }
    }
    public void tiaozhuan(Context a,Class b){
        Intent intent=new Intent();
        intent.setClass(a,b);
        startActivity(intent);

    }
}
