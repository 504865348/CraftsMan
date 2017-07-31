package com.joshua.craftsman.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.craftsHome.CraftsHomeActivity;
import com.joshua.craftsman.entity.HotCraftsman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/7/28.
 * 首页-热门-更多(工匠)
 */

public class HotMoreCraftAdapter extends RecyclerView.Adapter<HotMoreCraftAdapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<HotCraftsman> data = new ArrayList<>();

    public HotMoreCraftAdapter(Context context, List<HotCraftsman> data) {
        mInflater = LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.hot_more_crafts_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText(data.get(position).getCraftsmanName());
        holder.tv_info.setText(data.get(position).getIntroduction());
        Glide.with(mContext).load(data.get(position).getImageUrl()).placeholder(R.drawable.load_error).into(holder.iv_pic);
        holder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CraftsHomeActivity.class);
                intent.putExtra("craftsName", data.get(position).getCraftsmanName());
                intent.putExtra("craftsIntro", data.get(position).getIntroduction());
                intent.putExtra("craftsPic", data.get(position).getImageUrl());
                mContext.startActivity(intent);
            }
        });
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
        ImageView iv_pic;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.hot_more_crafts_name);
            tv_info = (TextView) itemView.findViewById(R.id.hot_more_crafts_info);
            iv_pic= (ImageView) itemView.findViewById(R.id.hot_more_crafts_cover);
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
