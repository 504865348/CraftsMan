package com.joshua.craftsman.activity.record;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.MyRecordAdapter;
import com.joshua.craftsman.entity.MyRecording;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.CamcorderProfile.get;

public class MyRecordActivity extends AppCompatActivity {

    private List<MyRecording> mMyRecordings = new ArrayList<>();
    private MyRecordAdapter mAdapter;
    private boolean isEmpty = false;
    @BindView(R.id.my_records_lv)
    ListView records_list;
    @BindView(R.id.empty_layout)
    FrameLayout empty_layout;
    @BindView(R.id.my_records_tool_bar)
    Toolbar my_records_tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_records);
        ButterKnife.bind(this);

        my_records_tool_bar.setTitle("");
        setSupportActionBar(my_records_tool_bar);

        GetVideoFiles(); // 获取所有文件，转化为 MyRecording 对象
        if (!isEmpty) {
            records_list.setVisibility(View.VISIBLE);
            empty_layout.setVisibility(View.GONE);
            mAdapter = new MyRecordAdapter(MyRecordActivity.this, mMyRecordings);
            records_list.setAdapter(mAdapter);
        } else {
            records_list.setVisibility(View.GONE);
            empty_layout.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 监听返回按钮
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 获取文件列表
     */
    public void GetVideoFiles() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            /**
             * 获取该文件夹下的所有文件
             */
            String path = Environment.getExternalStorageDirectory().getPath();
            File mediaStorageDir = new File(path + "/crafts_videos");
            File[] files = mediaStorageDir.listFiles();
            if (files == null) {
                isEmpty = true;
            } else {
                for (File file : files) {
                    Log.e("TAG", file.getName());
                    MyRecording myRecording = new MyRecording();
                    myRecording.setName(file.getName());
                    String publish_time = new SimpleDateFormat("yyyy年MM月dd日").format(file.lastModified());
                    myRecording.setTime(publish_time);
                    myRecording.setStorageUrl(file.getAbsolutePath());
                    mMyRecordings.add(myRecording);
                }
            }

        }
    }

}
