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
import com.joshua.craftsman.entity.Craftsman;
import com.joshua.craftsman.entity.HotCraftsman;

import java.util.ArrayList;
import java.util.List;


/**
 * ============================================================
 * <p>
 * 版 权 ： 吴奇俊  (c) 2017
 * <p>
 * 作 者 : 吴奇俊
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2017/4/24 13:56
 * <p>
 * 描 述 ：
 * <p>
 * ============================================================
 **/

public class HotCraftsAdapter extends android.support.v7.widget.RecyclerView.Adapter<HotCraftsAdapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<HotCraftsman> data = new ArrayList<>();

    public HotCraftsAdapter(Context context,List<HotCraftsman> data) {
        mInflater = LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.homepage_hot_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText(data.get(position).getCraftsmanName());
        Glide.with(mContext).load(data.get(position).getImageUrl()).placeholder(R.drawable.load_error).into(holder.iv_pic);
        holder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CraftsHomeActivity.class);
                intent.putExtra("craftsAccount", data.get(position).getCraftsAccount());
                intent.putExtra("craftsName", data.get(position).getCraftsmanName());
                intent.putExtra("craftsClassify", data.get(position).getClassifyCrafts());
                intent.putExtra("craftsHotDegree", data.get(position).getHotDegree());
                intent.putExtra("craftsIntro", data.get(position).getIntroduction());
                intent.putExtra("craftsPic", data.get(position).getImageUrl());
                intent.putExtra("isFocus", data.get(position).getIsFocus());
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
        ImageView iv_pic;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.hot_item_text);
            iv_pic= (ImageView) itemView.findViewById(R.id.hot_item_image);
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
