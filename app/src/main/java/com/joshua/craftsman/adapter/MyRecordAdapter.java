package com.joshua.craftsman.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.record.MyRecordActivity;
import com.joshua.craftsman.activity.record.PlayerFrameActivity;
import com.joshua.craftsman.activity.record.PostRecordActivity;
import com.joshua.craftsman.entity.MyRecording;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Lister on 2017-06-02.
 */

public class MyRecordAdapter extends BaseAdapter {

    private Context mContext;
    private List<MyRecording> mMyRecordings;

    public MyRecordAdapter(Context context, List<MyRecording> myRecordings) {
        this.mContext = context;
        this.mMyRecordings = myRecordings;
    }

    @Override
    public int getCount() {
        return mMyRecordings.size();
    }

    @Override
    public Object getItem(int position) {
        return mMyRecordings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.my_records_item, null);
        }

        ImageView record_item_cover = (ImageView) convertView.findViewById(R.id.record_item_cover);
        final TextView record_item_name = (TextView) convertView.findViewById(R.id.record_item_name);
        final TextView record_item_time = (TextView) convertView.findViewById(R.id.record_item_time);
        ImageButton record_item_release = (ImageButton) convertView.findViewById(R.id.record_item_release);
        ImageButton record_item_delete = (ImageButton) convertView.findViewById(R.id.record_item_delete);

        final MyRecording myRecording = mMyRecordings.get(position);
        record_item_name.setText(myRecording.getName());
        record_item_time.setText(myRecording.getTime());
        record_item_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=mMyRecordings.get(position).getStorageUrl();
                String title=mMyRecordings.get(position).getName();
                Intent intent=new Intent(mContext,PlayerFrameActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                mContext.startActivity(intent);
            }
        });
        record_item_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostRecordActivity.class);
                intent.putExtra("name", mMyRecordings.get(position).getName());
                mContext.startActivity(intent);
            }
        });
        record_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setTitle("确认")
                        .setMessage("是否删除该视频")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                File file = new File(myRecording.getStorageUrl());
                                if (file.exists()) {
                                    file.delete();
                                }
                                GetVideoFiles();
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });


        return convertView;
    }


    /**
     * 获取文件列表
     */
    public void GetVideoFiles(){
        mMyRecordings.clear();
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
