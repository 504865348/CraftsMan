package com.joshua.craftsman.adapter.billboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.adapter.find.FindFriendsAdapter;
import com.joshua.craftsman.entity.BillboardRankPro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/6/25.
 * 底部导航--榜单--节目榜单排名--适配器
 */

public class BillboardRankProAdapter extends RecyclerView.Adapter<BillboardRankProAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<BillboardRankPro> data = new ArrayList<>();

    public BillboardRankProAdapter(Context context, List<BillboardRankPro> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }
    @Override
    public BillboardRankProAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.billboard_rank_hot_item, parent, false);
        view.setOnClickListener(this);
        return new BillboardRankProAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillboardRankProAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getProBillboardTitle());
        holder.tv_top1.setText(data.get(position).getProTop1());
        holder.tv_topName1.setText(data.get(position).getProNameTop1());
        holder.tv_top2.setText(data.get(position).getProTop2());
        holder.tv_topName2.setText(data.get(position).getProNameTop2());
        Glide.with(mContext).load(data.get(position).getProBillboardImg()).into(holder.iv_pic);
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
        TextView tv_top1;
        TextView tv_topName1;
        TextView tv_top2;
        TextView tv_topName2;
        ImageView iv_pic;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.billboard_hot_name);
            tv_top1 = (TextView) itemView.findViewById(R.id.billboard_hot_top1);
            tv_topName1 = (TextView) itemView.findViewById(R.id.billboard_hot_program1);
            tv_top2 = (TextView) itemView.findViewById(R.id.billboard_hot_top2);
            tv_topName2 = (TextView) itemView.findViewById(R.id.billboard_hot_program2);
            iv_pic= (ImageView) itemView.findViewById(R.id.billboard_hot_img);
        }
    }

    private BillboardRankProAdapter.onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    interface  onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(BillboardRankProAdapter.onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public static class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {
        private BillboardRankProAdapter.onRecyclerViewItemClickListener mItemClickListener;
        private GestureDetector mGestureDetector;

        public RecyclerViewItemClickListener(Context pContext, BillboardRankProAdapter.onRecyclerViewItemClickListener pItemClickListener) {
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
