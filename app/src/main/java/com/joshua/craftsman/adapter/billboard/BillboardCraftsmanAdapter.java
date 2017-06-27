package com.joshua.craftsman.adapter.billboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.BillboardCraftsman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/6/1.
 * 底部导航--榜单--最大国工匠榜--适配器
 */

public class BillboardCraftsmanAdapter extends RecyclerView.Adapter<BillboardCraftsmanAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<BillboardCraftsman> data = new ArrayList<>();

    public BillboardCraftsmanAdapter(Context context, List<BillboardCraftsman> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.billboard_craftsman_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_rank.setText(data.get(position).getRankNumber());
        holder.tv_name.setText(data.get(position).getCraftsName());
        holder.tv_introduction.setText(data.get(position).getCraftsIntroduction());
        Glide.with(mContext).load(data.get(position).getCraftsImg()).into(holder.iv_pic);
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
        TextView tv_rank;
        TextView tv_name;
        TextView tv_introduction;
        ImageView iv_pic;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_rank = (TextView) itemView.findViewById(R.id.billboard_craftsman_rank);
            tv_name = (TextView) itemView.findViewById(R.id.billboard_craftsman_name);
            tv_introduction = (TextView) itemView.findViewById(R.id.billboard_craftsman_introduction);
            iv_pic= (ImageView) itemView.findViewById(R.id.billboard_craftsman_img);
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