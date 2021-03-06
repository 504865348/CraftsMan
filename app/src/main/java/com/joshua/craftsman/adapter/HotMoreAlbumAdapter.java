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
import com.joshua.craftsman.activity.albumHome.AlbumHomeActivity;
import com.joshua.craftsman.entity.HotSkills;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/7/28.
 * 首页-热门-更多(专辑--匠心独运，讲政策，听专题，看利器)
 */

public class HotMoreAlbumAdapter extends RecyclerView.Adapter<HotMoreAlbumAdapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<HotSkills> data = new ArrayList<>();

    public HotMoreAlbumAdapter(Context context, List<HotSkills> data) {
        mInflater = LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.hot_more_albums_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText(data.get(position).getProgramName());
        holder.tv_info.setText(data.get(position).getIntroduction());
        holder.tv_craft.setText(data.get(position).getAuthor());
        holder.tv_classify.setText(data.get(position).getClassify());
        Glide.with(mContext).load(data.get(position).getImageUrl()).placeholder(R.drawable.load_error).into(holder.iv_pic);
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
        TextView tv_craft;
        TextView tv_classify;
        ImageView iv_pic;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.hot_albums_name);
            tv_info = (TextView) itemView.findViewById(R.id.hot_albums_info);
            tv_craft = (TextView) itemView.findViewById(R.id.hot_albums_craft);
            tv_classify = (TextView) itemView.findViewById(R.id.hot_albums_classification);
            iv_pic= (ImageView) itemView.findViewById(R.id.hot_albums_cover);
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
