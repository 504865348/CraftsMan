package com.joshua.craftsman.adapter.qacrafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joshua.craftsman.R;
import com.joshua.craftsman.entity.CraftsMyQues;
import com.joshua.craftsman.entity.joshua.QuesAnsClassify;

import java.util.ArrayList;
import java.util.List;

import static com.joshua.craftsman.R.id.answer_audio_duration;
import static com.joshua.craftsman.R.id.answer_audio_people_listened;

/**
 * Created by nzz on 2017/6/9.
 * 工匠-我的问答-我的提问-适配器
 */

public class QuesAdapter extends RecyclerView.Adapter<QuesAdapter.MyViewHolder> implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<QuesAnsClassify> data = new ArrayList<>();
    public QuesAdapter(Context context,List<QuesAnsClassify> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }
    @Override
    public QuesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.my_ask_answer_crafts_ans_item, parent, false);
        view.setOnClickListener(this);
        return new QuesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuesAdapter.MyViewHolder holder, int position) {
        holder.tv_asker.setText(data.get(position).getUserId());
//        holder.tv_price.setText(data.get(position).getPrice());
        holder.tv_price.setText("￥1");
        holder.tv_content.setText(data.get(position).getQuestionWord());
        holder.tv_time.setText(data.get(position).getAnsterTime());
        holder.answer_audio_duration.setText(data.get(position).getVedioTimes());
        holder.answer_audio_people_listened.setText(data.get(position).getListenNumber());
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
        TextView tv_time;
        TextView answer_audio_duration;
        TextView  answer_audio_people_listened;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_asker = (TextView) itemView.findViewById(R.id.answer_tv_asker);
            tv_price = (TextView) itemView.findViewById(R.id.answer_tv_ask_price);
            tv_content = (TextView) itemView.findViewById(R.id.answer_tv_ask_content);
            tv_time = (TextView) itemView.findViewById(R.id.answer_tv_time);
            answer_audio_duration= (TextView) itemView.findViewById(R.id.answer_audio_duration);
            answer_audio_people_listened= (TextView) itemView.findViewById(R.id.answer_audio_people_listened);
        }
    }

    private QuesAdapter.onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    interface onRecyclerViewItemClickListener {
        void onItemClick(View view, String position);
    }

    public void setOnRecyclerViewItemClickListener(QuesAdapter.onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

}
