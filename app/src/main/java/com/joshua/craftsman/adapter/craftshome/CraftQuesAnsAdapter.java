package com.joshua.craftsman.adapter.craftshome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.CraftHomeAns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/7/22.
 * 工匠个人主页--问答
 */

public class CraftQuesAnsAdapter extends android.support.v7.widget.RecyclerView.Adapter<CraftQuesAnsAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<CraftHomeAns> data = new ArrayList<>();

    public CraftQuesAnsAdapter(Context context,List<CraftHomeAns> data) {
        mInflater = LayoutInflater.from(context);
        this.data=data;
        this.mContext=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.craft_q_a_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_asker.setText(data.get(position).getAskName());
        holder.tv_price.setText(data.get(position).getPrice());
        holder.tv_content.setText(data.get(position).getContent());
        holder.tv_duration.setText(data.get(position).getTimeLength());
        holder.tv_listenPeople.setText(data.get(position).getListenrNumber());
        holder.tv_askTime.setText(data.get(position).getTime());
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
        TextView tv_asker;
        TextView tv_price;
        TextView tv_content;
        TextView tv_duration;
        TextView tv_listenPeople;
        TextView tv_askTime;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_asker = (TextView) itemView.findViewById(R.id.craft_q_a_asker);
            tv_price = (TextView) itemView.findViewById(R.id.craft_q_a_ask_price);
            tv_content = (TextView) itemView.findViewById(R.id.craft_q_a_ask_content);
            tv_duration = (TextView) itemView.findViewById(R.id.craft_q_a_answer_audio_duration);
            tv_listenPeople = (TextView) itemView.findViewById(R.id.craft_q_a_answer_audio_people_listened);
            tv_askTime = (TextView) itemView.findViewById(R.id.craft_q_a_answer_tv_time);
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

