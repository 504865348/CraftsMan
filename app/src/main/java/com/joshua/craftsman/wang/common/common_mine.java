package com.joshua.craftsman.wang.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


import com.joshua.craftsman.R;
import com.joshua.craftsman.wang.common.craft.mine;
import com.joshua.craftsman.wang.common.craft.question;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 18012 on 2017/4/29.
 */

public class common_mine extends AppCompatActivity {
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
                tiaozhuan(common_mine.this, common_craft.class);

                break;
            case R.id.orderform:

                break;
            case R.id.money:
                tiaozhuan(common_mine.this, common_money.class);

                break;
            case R.id.question:
                tiaozhuan(common_mine.this, common_myquestion.class);

                break;
            case R.id.opinion:
                tiaozhuan(common_mine.this, common_question.class);

                break;
            case R.id.set:
                tiaozhuan(common_mine.this, common_set.class);

                break;
        }
    }
    public void tiaozhuan(Context a, Class b){
        Intent intent=new Intent();
        intent.setClass(a,b);
        startActivity(intent);

    }
}
