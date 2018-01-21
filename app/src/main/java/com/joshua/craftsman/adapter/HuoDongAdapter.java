package com.joshua.craftsman.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.find.FindActivityDetailActivity;
import com.joshua.craftsman.entity.FindFriendsAttention;
import com.joshua.craftsman.entity.joshua.HuoDong;

import java.util.ArrayList;
import java.util.List;


public class HuoDongAdapter extends RecyclerView.Adapter<HuoDongAdapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<HuoDong> data = new ArrayList<>();

    public HuoDongAdapter(Context context, List<HuoDong> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }

    @Override
    public HuoDongAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.huo_dong_item, parent, false);
        view.setOnClickListener(this);
        return new HuoDongAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HuoDongAdapter.MyViewHolder holder, final int position) {
        holder.tv_title.setText(data.get(position).getactivityName());
        holder.tv_time.setText(data.get(position).getactivityDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, FindActivityDetailActivity.class);
                intent.putExtra("huodong",data.get(position));
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_time;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);

        }
    }

    private HuoDongAdapter.onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    public interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
