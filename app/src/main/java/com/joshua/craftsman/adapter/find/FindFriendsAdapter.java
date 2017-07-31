package com.joshua.craftsman.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.craftsHome.CraftsHomeActivity;
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
    public void onBindViewHolder(FindFriendsAdapter.MyViewHolder holder, final int position) {
        holder.tv_name.setText(data.get(position).getCraftsmanName());
        holder.tv_introduction.setText(data.get(position).getIntroduction());
        Glide.with(mContext).load(data.get(position).getImageUrl()).placeholder(R.drawable.load_error).into(holder.iv_pic);
        holder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CraftsHomeActivity.class);
                intent.putExtra("craftsName", data.get(position).getCraftsmanName());
                //intent.putExtra("craftsIntro", data.get(position).getIntroduction());
                intent.putExtra("craftsPic", data.get(position).getImageUrl());
                mContext.startActivity(intent);
            }
        });
        holder.btn_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "暂未开放关注功能", Toast.LENGTH_SHORT).show();
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
        ImageView iv_pic;
        TextView tv_name;
        TextView tv_introduction;
        Button btn_attention;
        MyViewHolder(View itemView) {
            super(itemView);
            iv_pic= (ImageView) itemView.findViewById(R.id.friends_recommend_img);
            tv_name = (TextView) itemView.findViewById(R.id.friends_recommend_name);
            btn_attention = (Button) itemView.findViewById(R.id.friends_recommend_plus);
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

    public static class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {
        private FindFriendsAdapter.onRecyclerViewItemClickListener mItemClickListener;
        private GestureDetector mGestureDetector;

        public RecyclerViewItemClickListener(Context pContext, FindFriendsAdapter.onRecyclerViewItemClickListener pItemClickListener) {
            mItemClickListener = pItemClickListener;
            mGestureDetector = new GestureDetector(pContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            int position = rv.getChildAdapterPosition(childView);
            String pos = Integer.toString(position);
            if (childView != null && position != RecyclerView.NO_POSITION && mItemClickListener != null
                    && mGestureDetector.onTouchEvent(e)) {
                mItemClickListener.onItemClick(childView, pos);
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}
