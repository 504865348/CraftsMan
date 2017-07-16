package com.joshua.craftsman.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.ask.AskQuestionActivity;
import com.joshua.craftsman.entity.QuesAnsClassify;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.utils.AudioRecoderUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.os.Build.VERSION_CODES.M;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

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

public class QuesAnsClassifyAdapter extends android.support.v7.widget.RecyclerView.Adapter<QuesAnsClassifyAdapter.MyViewHolder> implements MediaPlayer.OnCompletionListener {
    private LayoutInflater mInflater;
    private Activity mContext;
    private List<QuesAnsClassify> data = new ArrayList<>();
    private OkHttpClient mOkHttpClient;
    private ProgressDialog mDialog;
    private Call mCall;
    private static MediaPlayer music;
    private File mFile;
    private boolean isFinishPlaying = true;


    public QuesAnsClassifyAdapter(Activity context, List<QuesAnsClassify> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
        mOkHttpClient = new OkHttpClient();
        music = new MediaPlayer();
        music.setOnCompletionListener(this);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.q_a_item, parent, false);
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
        holder.rl_play_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = data.get(position).getId();
                //首先判断是否已经下载
                if (checkLocal(id)) {
                    //录音的播放与暂停
                    playRecord();
                } else {
                    mDialog = new ProgressDialog(mContext);
                    mDialog.setMessage("音频下载中，请稍后");
                    mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            mCall.cancel();
                            Toast.makeText(mContext, "任务已取消", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mDialog.show();
                    getSoundFromServer(id);
                }
            }
        });
        holder.itemView.setTag(position + "");
    }

    /**
     * 判断音频文件是否存在
     */
    private boolean checkLocal(String id) {
        mFile = new File(AudioRecoderUtils.RECODE_PATH, id + ".amr");
        return mFile.exists();
    }

    /**
     * 录音播放
     */
    private void playRecord() {
        if (mFile.exists()) {
            if (music.isPlaying()) {
                music.pause();
            } else {
                try {
                    if (isFinishPlaying) {
                        music = new MediaPlayer();
                        music.setDataSource(mFile.getAbsolutePath());
                        music.prepare();
                        isFinishPlaying = false;
                    }
                    music.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            Toast.makeText(mContext, "出现异常，请反馈", Toast.LENGTH_SHORT).show();
        }
    }

    //访问网络，获取音频流，下载，准备播放
    private void getSoundFromServer(final String id) {
        RequestBody params = new FormBody.Builder()
                .add("method", Server.QUERY_QUESTION)
                .add("Id", id)
                .build();
        Request request = new Request.Builder()
                .post(params)
                .url(Server.SERVER_REMOTE).build();
        mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(AudioRecoderUtils.RECODE_PATH, id + ".amr");
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        Log.d("h_bl", "progress=" + progress);
//                        Message msg = mHandler.obtainMessage();
//                        msg.what = 1;
//                        msg.arg1 = progress;
//                        mHandler.sendMessage(msg);
                    }
                    fos.flush();
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "下载成功", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    });

                } catch (Exception e) {
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    });

                } finally {
                    if (is != null)
                        is.close();
                    if (fos != null)
                        fos.close();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        isFinishPlaying = true;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_craftsImage;
        TextView tv_craftsName;
        TextView tv_introduction;
        TextView tv_content;
        TextView tv_listenrNumber;
        TextView tv_time;

        ImageView iv_q_a_item_go_ask;
        RelativeLayout rl_play_sound;

        MyViewHolder(View itemView) {
            super(itemView);
            iv_craftsImage = (ImageView) itemView.findViewById(R.id.q_a_item_icon);
            tv_craftsName = (TextView) itemView.findViewById(R.id.q_a_item_name);
            tv_introduction = (TextView) itemView.findViewById(R.id.q_a_item_introduction);
            tv_content = (TextView) itemView.findViewById(R.id.q_a_item_question);
            tv_listenrNumber = (TextView) itemView.findViewById(R.id.q_a_people_content);
            tv_time = (TextView) itemView.findViewById(R.id.q_a_audio_duration);
            iv_q_a_item_go_ask = (ImageView) itemView.findViewById(R.id.q_a_item_go_ask);
            rl_play_sound = (RelativeLayout) itemView.findViewById(R.id.rl_play_sound);

        }
    }

}
