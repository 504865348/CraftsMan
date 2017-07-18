package com.joshua.craftsman.adapter.craftshome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/7/17.
 * 工匠个人主页--专辑
 */

public class CraftAlbumAdapter extends android.support.v7.widget.RecyclerView.Adapter<CraftAlbumAdapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<Album> data = new ArrayList<>();


    public CraftAlbumAdapter(Context context,List<Album> data) {
        mInflater = LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.craft_album_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_classify.setText(data.get(position).getClassifyName());
        //holder.tv_introduction.setText(data.get(position).getIntroduction());
        Glide.with(mContext).load(data.get(position).getAlbumImage()).placeholder(R.drawable.load_error).into(holder.iv_pic);
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
        TextView tv_classify;
        //TextView tv_introduction;
        ImageView iv_pic;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.craft_album_title);
            tv_classify = (TextView) itemView.findViewById(R.id.craft_album_classify);
           // tv_introduction = (TextView) itemView.findViewById(R.id.craft_album_introduction);
            iv_pic= (ImageView) itemView.findViewById(R.id.craft_album_img);
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
