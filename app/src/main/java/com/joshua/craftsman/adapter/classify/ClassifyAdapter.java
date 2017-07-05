package com.joshua.craftsman.adapter.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.Classify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/6/17.
 * 底部导航--分类--适配器
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Classify> data = new ArrayList<>();

    public ClassifyAdapter(Context context, List<Classify> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }
    @Override
    public ClassifyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.classify_item, parent, false);
        view.setOnClickListener(this);
        return new ClassifyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassifyAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getRecordTitle());
        holder.tv_author.setText(data.get(position).getName());
        Glide.with(mContext).load(data.get(position).getRecordImage()).into(holder.iv_pic);
        holder.itemView.setTag(position+"");
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnRecyclerViewItemClickListener != null) {
            mOnRecyclerViewItemClickListener.onItemClick(v, (String) v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_author;
        ImageView iv_pic;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.classify_house_title);
            tv_author = (TextView) itemView.findViewById(R.id.classify_house_author);
            iv_pic= (ImageView) itemView.findViewById(R.id.classify_house_img);
        }
    }

    private ClassifyAdapter.onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(ClassifyAdapter.onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}

