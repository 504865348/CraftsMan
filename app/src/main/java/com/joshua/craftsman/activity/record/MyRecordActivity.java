package com.joshua.craftsman.activity.record;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class MyRecordActivity extends AppCompatActivity {

    private List<MyRecording> mMyRecordings = new ArrayList<>();
    private MyRecordAdapter mAdapter;

    @BindView(R.id.my_records_lv)
    ListView records_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_records);
        ButterKnife.bind(this);

        GetVideoFiles(); // 获取所有文件，转化为 MyRecording 对象

        mAdapter = new MyRecordAdapter(MyRecordActivity.this, mMyRecordings);
        records_list.setAdapter(mAdapter);
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
                mMyRecordings.add(myRecording);
            }
        }
    }

}
