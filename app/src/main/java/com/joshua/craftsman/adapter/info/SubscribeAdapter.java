package com.joshua.craftsman.adapter.info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.MySubscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/8/5.
 * 订阅适配器(专辑)
 */

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<MySubscribe> data = new ArrayList<>();

    public SubscribeAdapter(Context context, List<MySubscribe> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.info_subscribe_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(data.get(position).getProgramName());
        holder.tv_info.setText(data.get(position).getIntroduction());
        holder.tv_classify.setText(data.get(position).getClassify());
        holder.tv_model.setText(data.get(position).getModel());
        Glide.with(mContext).load(data.get(position).getUrl()).placeholder(R.drawable.load_error).into(holder.iv_pic);
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
        TextView tv_name;
        TextView tv_info;
        TextView tv_classify;
        TextView tv_model;
        ImageView iv_pic;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.subscribe_album_name);
            tv_info = (TextView) itemView.findViewById(R.id.subscribe_album_info);
            tv_classify = (TextView) itemView.findViewById(R.id.subscribe_album_classify);
            tv_model = (TextView) itemView.findViewById(R.id.subscribe_album_model);
            iv_pic= (ImageView) itemView.findViewById(R.id.subscribe_album_pic);
        }
    }

    private onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    public interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}