package com.joshua.craftsman.adapter.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.FindFriendsAttention;

import java.util.ArrayList;
import java.util.List;


public class FindFriendsAdapter extends android.support.v7.widget.RecyclerView.Adapter<FindFriendsAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<FindFriendsAttention> data = new ArrayList<>();

    public FindFriendsAdapter(Context context,List<FindFriendsAttention> data) {
        mInflater = LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }
    @Override
    public FindFriendsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.find_friends_recommend_item, parent, false);
        view.setOnClickListener(this);
        return new FindFriendsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FindFriendsAdapter.MyViewHolder holder, int position) {
        holder.tv_name.setText(data.get(position).getCraftsName());
        holder.tv_introduction.setText(data.get(position).getIntroduction());
        Glide.with(mContext).load(data.get(position).getCraftsImage()).placeholder(R.drawable.load_error).into(holder.iv_pic);
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
        ImageView iv_pic;
        TextView tv_name;
        TextView tv_introduction;
        MyViewHolder(View itemView) {
            super(itemView);
            iv_pic= (ImageView) itemView.findViewById(R.id.friends_recommend_img);
            tv_name = (TextView) itemView.findViewById(R.id.friends_recommend_name);
            tv_introduction = (TextView) itemView.findViewById(R.id.friends_recommend_introduction);
        }
    }

    private FindFriendsAdapter.onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(FindFriendsAdapter.onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
