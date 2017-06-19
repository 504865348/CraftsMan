package com.joshua.craftsman.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.ask.AskQuestionActivity;
import com.joshua.craftsman.entity.QuesAnsClassify;

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
 * 创建日期 ： 2017/6/1 14:18
 * <p>
 * 描 述 ：问答-列表适配器
 * <p>
 * ============================================================
 **/

public class QuesAnsClassifyAdapter extends android.support.v7.widget.RecyclerView.Adapter<QuesAnsClassifyAdapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<QuesAnsClassify> data = new ArrayList<>();

    public QuesAnsClassifyAdapter(Context context, List<QuesAnsClassify> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.q_a_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext).load(data.get(position).getCraftsImage()).placeholder(R.drawable.load_error).into(holder.iv_craftsImage);
        holder.tv_craftsName.setText(data.get(position).getCraftsName());
        holder.tv_introduction.setText(data.get(position).getIntroduction());
        holder.tv_content.setText(data.get(position).getContent());
        holder.tv_listenrNumber.setText(data.get(position).getListenrNumber() + "人听过");
        holder.tv_time.setText(data.get(position).getTime());
        holder.iv_q_a_item_go_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AskQuestionActivity.class);
                intent.putExtra("answer", data.get(position).getCraftsName());
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setTag(position + "");
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
        ImageView iv_craftsImage;
        TextView tv_craftsName;
        TextView tv_introduction;
        TextView tv_content;
        TextView tv_listenrNumber;
        TextView tv_time;

        ImageView iv_q_a_item_go_ask;


        MyViewHolder(View itemView) {
            super(itemView);
            iv_craftsImage = (ImageView) itemView.findViewById(R.id.q_a_item_icon);
            tv_craftsName = (TextView) itemView.findViewById(R.id.q_a_item_name);
            tv_introduction = (TextView) itemView.findViewById(R.id.q_a_item_introduction);
            tv_content = (TextView) itemView.findViewById(R.id.q_a_item_question);
            tv_listenrNumber = (TextView) itemView.findViewById(R.id.q_a_people_content);
            tv_time = (TextView) itemView.findViewById(R.id.q_a_audio_duration);
            iv_q_a_item_go_ask = (ImageView) itemView.findViewById(R.id.q_a_item_go_ask);

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
