package com.joshua.craftsman.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.record.PostRecordActivity;
import com.joshua.craftsman.entity.MyRecording;

import java.io.File;
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
                File file = new File(myRecording.getStorageUrl());
                if (file.exists()) {
                   file.delete();
                }
            }
        });


        return convertView;
    }
}
