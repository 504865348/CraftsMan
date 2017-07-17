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

    @BindView(R.id.my_records_lv)
    ListView records_list;
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

        mAdapter = new MyRecordAdapter(MyRecordActivity.this, mMyRecordings);
        records_list.setAdapter(mAdapter);
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
    public void GetVideoFiles(){

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            /**
             * 获取该文件夹下的所有文件
             */
            String path = Environment.getExternalStorageDirectory().getPath();
            File mediaStorageDir = new File(path + "/crafts_videos");
            File[] files = mediaStorageDir.listFiles();

            for(int i = 0; i < files.length; i++){
                Log.e("TAG", files[i].getName());
                MyRecording myRecording = new MyRecording();
                myRecording.setName(files[i].getName());
                String publish_time = new SimpleDateFormat("yyyy年MM月dd日").format(files[i].lastModified());
                myRecording.setTime(publish_time);
                myRecording.setStorageUrl(files[i].getAbsolutePath());
                mMyRecordings.add(myRecording);
            }
        }
    }

}
