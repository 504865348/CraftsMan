package com.joshua.craftsman.activity.fuctions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joshua.craftsman.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeHotActivity extends AppCompatActivity {
    View viewTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_hot);
        ButterKnife.bind(this);
        onViewClicked(viewTemp);
    }

    @OnClick({R.id.hot_middle_image_crafts, R.id.hot_middle_image_skills, R.id.hot_middle_image_policy, R.id.hot_middle_image_listen, R.id.hot_middle_image_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hot_middle_image_crafts:
                JumpIntoPage(CraftsActivity.class);
                break;
            case R.id.hot_middle_image_skills:
                JumpIntoPage(SkillsActivity.class);
                break;
            case R.id.hot_middle_image_policy:
                JumpIntoPage(PolicyActivity.class);
                break;
            case R.id.hot_middle_image_listen:
                JumpIntoPage(ListenActivity.class);
                break;
            case R.id.hot_middle_image_look:
                JumpIntoPage(LookActivity.class);
                break;
        }
    }
    public void JumpIntoPage(Class<?> className) {
        Intent intent = new Intent(HomeHotActivity.this,className);
        startActivity(intent);
    }
}
