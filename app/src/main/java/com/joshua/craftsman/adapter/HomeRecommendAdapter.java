package com.joshua.craftsman.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.HomeRecommend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/4/31.
 * 首页-推荐--适配器
 */

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<HomeRecommend> data = new ArrayList<>();

    public HomeRecommendAdapter(Context context,List<HomeRecommend> data) {
        mInflater = LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_page_recommend_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(data.get(position).getTitle());
        Glide.with(mContext).load(data.get(position).getProgramImage()).into(holder.iv_pic);
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
        ImageView iv_pic;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.recommend_item_text_name);
            iv_pic= (ImageView) itemView.findViewById(R.id.recommend_item_cover);
        }
    }

    private onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

}