package com.joshua.craftsman.adapter.qacrafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.CraftsMyAns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nzz on 2017/6/7.
 * 工匠-我的问答-我的回答-适配器
 */

public class AnsAdapter extends RecyclerView.Adapter<AnsAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<CraftsMyAns> data = new ArrayList<>();
    public AnsAdapter(Context context,List<CraftsMyAns> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }
    @Override
    public AnsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.my_ask_answer_crafts_ans_item, parent, false);
        view.setOnClickListener(this);
        return new AnsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AnsAdapter.MyViewHolder holder, int position) {
        holder.tv_asker.setText(data.get(position).getAskName());
        holder.tv_price.setText(data.get(position).getPrice());
        holder.tv_content.setText(data.get(position).getContent());
        holder.tv_timeLength.setText(data.get(position).getTimeLength());
        holder.tv_listenrNumber.setText(data.get(position).getListenrNumber());
        holder.tv_time.setText(data.get(position).getTime());
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
        TextView tv_timeLength;
        TextView tv_listenrNumber;
        TextView tv_time;

        MyViewHolder(View itemView) {
            super(itemView);
            tv_asker = (TextView) itemView.findViewById(R.id.answer_tv_asker);
            tv_price = (TextView) itemView.findViewById(R.id.answer_tv_ask_price);
            tv_content = (TextView) itemView.findViewById(R.id.answer_tv_ask_content);
            tv_timeLength = (TextView) itemView.findViewById(R.id.answer_audio_duration);
            tv_listenrNumber = (TextView) itemView.findViewById(R.id.answer_audio_people_listened);
            tv_time = (TextView) itemView.findViewById(R.id.answer_tv_time);
        }
    }

    private AnsAdapter.onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(AnsAdapter.onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
