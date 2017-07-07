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
import com.joshua.craftsman.entity.BillboardRankCrafts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/6/25.
 * 底部导航--榜单--工匠榜单排名--适配器
 */

public class BillboardRankCraftsAdapter extends RecyclerView.Adapter<BillboardRankCraftsAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<BillboardRankCrafts> data = new ArrayList<>();

    public BillboardRankCraftsAdapter(Context context, List<BillboardRankCrafts> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }
    @Override
    public BillboardRankCraftsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.billboard_rank_crafts_item, parent, false);
        view.setOnClickListener(this);
        return new BillboardRankCraftsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillboardRankCraftsAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getCraftsBillboardTitle());
        holder.tv_top1.setText(data.get(position).getCraftsTop1());
        holder.tv_topName1.setText(data.get(position).getCraftsNameTop1());
        holder.tv_top2.setText(data.get(position).getCraftsTop2());
        holder.tv_topName2.setText(data.get(position).getCraftsNameTop2());
        Glide.with(mContext).load(data.get(position).getCraftsBillboardImg()).into(holder.iv_pic);
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
        TextView tv_top1;
        TextView tv_topName1;
        TextView tv_top2;
        TextView tv_topName2;
        ImageView iv_pic;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.billboard_crafts_name);
            tv_top1 = (TextView) itemView.findViewById(R.id.billboard_crafts_top1);
            tv_topName1 = (TextView) itemView.findViewById(R.id.billboard_crafts1);
            tv_top2 = (TextView) itemView.findViewById(R.id.billboard_crafts_top2);
            tv_topName2 = (TextView) itemView.findViewById(R.id.billboard_crafts2);
            iv_pic= (ImageView) itemView.findViewById(R.id.billboard_crafts_img);
        }
    }

    private BillboardRankCraftsAdapter.onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(BillboardRankCraftsAdapter.onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
