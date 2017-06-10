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
import com.joshua.craftsman.entity.BillboardPay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/5/31.
 * 底部导航--榜单--付费精品畅销榜--适配器
 */

public class BillboardPayAdapter extends RecyclerView.Adapter<BillboardPayAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<BillboardPay> data = new ArrayList<>();

    public BillboardPayAdapter(Context context, List<BillboardPay> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.billboard_pay_program_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_rank.setText(data.get(position).getRankNumber());
        holder.tv_title.setText(data.get(position).getProgramTitle());
        holder.tv_author.setText(data.get(position).getAuthorName());
        Glide.with(mContext).load(data.get(position).getProgramImg()).into(holder.iv_pic);
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
        TextView tv_title;
        TextView tv_author;
        ImageView iv_pic;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_rank = (TextView) itemView.findViewById(R.id.billboard_pay_program_rank);
            tv_title = (TextView) itemView.findViewById(R.id.billboard_pay_program_name);
            tv_author = (TextView) itemView.findViewById(R.id.billboard_pay_program_author_name);
            iv_pic= (ImageView) itemView.findViewById(R.id.billboard_pay_program_img);
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
