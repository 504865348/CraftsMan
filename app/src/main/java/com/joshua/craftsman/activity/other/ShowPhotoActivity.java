package com.joshua.craftsman.activity.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;

import static com.joshua.craftsman.R.id.iv_ques;

public class ShowPhotoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        ImageView imageView= (ImageView) findViewById(R.id.iv_pic);
        String pic=getIntent().getStringExtra("pic");
        Glide.with(this).load(pic).error(R.drawable.load_error).into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
