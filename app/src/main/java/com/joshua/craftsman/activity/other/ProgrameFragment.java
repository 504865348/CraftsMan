package com.joshua.craftsman.activity.other;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.albumHome.AlbumHomeActivity;
import com.joshua.craftsman.activity.record.PlayerFrameActivity;
import com.joshua.craftsman.adapter.HomeRecommendAdapter;
import com.joshua.craftsman.application.BaseApplication;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.entity.joshua.OrderType;
import com.joshua.craftsman.entity.joshua.VideoDetail;
import com.joshua.craftsman.fragment.BaseFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.pay.event.MessageEvent;
import com.joshua.craftsman.pay.global.PayAction;
import com.joshua.craftsman.pay.global.PopWindowUtils;
import com.joshua.craftsman.pay.util.PaySuccess;
import com.joshua.craftsman.pay.util.PayUtils;
import com.joshua.craftsman.utils.AudioRecoderUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * “匠心独运”、“讲政策”“听专题”“看利器”点击进入后，点击节目时没有出现付款页面，直接闪退
 * 原因，接口返回值不规范
 * 所以直接复制了首页-热门的代码
 * 换了个接口名字
 */
public class ProgrameFragment extends BaseFragment {


    @BindView(R.id.home_recommend_rv)
    RecyclerView home_recommend_rv;

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    private String albumId = AlbumHomeActivity.homeAlbumId;
    private List<VideoDetail> list_TJ;

    private Call mCall;
    private File mFile;
    private OkHttpClient mOkHttpClient;


    private ProgressDialog mDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = msg.arg1;
            mDialog.setProgress(progress);
        }
    };
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public ProgrameFragment() {
    }



    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.home_page_recommend, null);
        mOkHttpClient = new OkHttpClient();
        mDialog = new ProgressDialog(mContext);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setMax(100);
        initRefreshRecycleView(view);
        return view;
    }

    private void initRefreshRecycleView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }
        });
    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: ");
        list_TJ = new ArrayList<>();
        getDataFromServer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void getDataFromServer() {
        getTJ();//推荐
    }

    private void getTJ() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getActivity()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.PRO_LIST_BY_NAME)
                .add("albumId", albumId)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(getActivity()) {
            @Override
            protected void success(String result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });

                parseTJ(result);
            }

            @Override
            protected void error() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
            }
        });
    }

    private void parseTJ(String result) {
        Log.d("pro", "parseTJ: "+result);
        Gson gson = new Gson();
        list_TJ = gson.fromJson(result, new TypeToken<List<VideoDetail>>() {
        }.getType());
        if (list_TJ.get(0).getRecordTitle().equals("null")) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(true);
                }
            });
        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setEmptyView(false);
                    initRecycleTJ();
                }
            });
        }
    }

    private void initRecycleTJ() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        home_recommend_rv.setLayoutManager(linearLayoutManager);
        HomeRecommendAdapter adapter = new HomeRecommendAdapter(getActivity(), list_TJ);
        adapter.setOnRecyclerViewItemClickListener(new HomeRecommendAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String position) {
                final int pos = Integer.parseInt(position);
                final String id = list_TJ.get(pos).getId();
                final String url = list_TJ.get(pos).getDownloadUrl();
                final String title = list_TJ.get(pos).getRecordTitle();
                final String isPay = list_TJ.get(pos).getIsPay();


                PopWindowUtils.showPop(getActivity(), ll_content, new PayAction() {
                    @Override
                    public void aliPay() {
                        PayUtils utils = new PayUtils(getActivity());
                        utils.setPaySuccess(new PaySuccess() {
                            @Override
                            public void onSuccess(String o) {
                                getDataFromServer();
                                Log.d(TAG, "onSuccess: pay success");
                                //首先判断是否已经下载
                                Toast.makeText(BaseApplication.getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
                                //取消下载功能，直接在线播放
                                Intent intent = new Intent(getActivity(), PlayerFrameActivity.class);
                                intent.putExtra("url",url);
                                intent.putExtra("title", title);
                                intent.putExtra("entity", list_TJ.get(pos));
                                startActivity(intent);
                            }
                        });

                        if(isPay.equals("true")){
                            //取消下载功能，直接在线播放
                            Intent intent = new Intent(getActivity(), PlayerFrameActivity.class);
                            intent.putExtra("url",url);
                            intent.putExtra("title", title);
                            intent.putExtra("entity", list_TJ.get(pos));
                            startActivity(intent);
                        }else{
                            //没有支付跳转支付
                            utils.payV2(OrderType.TYPE_BYE_VIDEO, list_TJ.get(pos).getId(),  Float.parseFloat(list_TJ.get(pos).getMoney()));
                        }
                    }

                    @Override
                    public void wxPay() {
                        if(isPay.equals("true")){
                            //取消下载功能，直接在线播放
                            Intent intent = new Intent(getActivity(), PlayerFrameActivity.class);
                            intent.putExtra("url",url);
                            intent.putExtra("title", title);
                            intent.putExtra("entity", list_TJ.get(pos));
                            startActivity(intent);
                        }else {
                            PayUtils utils = new PayUtils(getActivity());
                            utils.setPaySuccess(new PaySuccess() {
                                @Override
                                public void onSuccess(String o) {
                                    getDataFromServer();
                                    Log.d(TAG, "onSuccess: pay success");
                                    //首先判断是否已经下载
                                    Toast.makeText(BaseApplication.getApplication(), "支付成功", Toast.LENGTH_SHORT).show();
                                    //取消下载功能，直接在线播放
                                    Intent intent = new Intent(getActivity(), PlayerFrameActivity.class);
                                    intent.putExtra("url",url);
                                    intent.putExtra("title", title);
                                    intent.putExtra("entity", list_TJ.get(pos));
                                    startActivity(intent);
                                }
                            });
                            utils.payWx(OrderType.TYPE_BYE_VIDEO, list_TJ.get(pos).getId(), Float.parseFloat(list_TJ.get(pos).getMoney()));
                        }

                    }
                });
            }
        });
        home_recommend_rv.setAdapter(adapter);
    }

    /**
     * 判断音频文件是否存在
     */
    private boolean checkLocal(String id) {
        mFile = new File(AudioRecoderUtils.VIDEO_PATH, "video_" + id + ".mp4");
        return mFile.exists();
    }


    //访问网络，获取音频流，下载，准备播放
    private void getSoundFromServer(final String id, final String url, final String title, final int pos) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                        mFile.delete();
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
                    //首先要创建文件夹
                    File path = new File(AudioRecoderUtils.VIDEO_PATH);
                    if (!path.exists())
                        path.mkdirs();
                    File file = new File(AudioRecoderUtils.VIDEO_PATH, "video_" + id + ".mp4");
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        Log.d("h_bl", "progress=" + progress);

                        Message msg = mHandler.obtainMessage();
                        msg.what = 1;
                        msg.arg1 = progress;
                        mHandler.sendMessage(msg);
                    }
                    fos.flush();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "下载成功", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                            //录音的播放与暂停
                            mFile = new File(AudioRecoderUtils.VIDEO_PATH, "video_" + id + ".mp4");
                            Intent intent = new Intent(mContext, PlayerFrameActivity.class);
                            intent.putExtra("url", mFile.getAbsolutePath());
                            intent.putExtra("title", title);
                            intent.putExtra("entity", list_TJ.get(pos));
                            startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                            mFile.delete();
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

    private void setEmptyView(Boolean isEmpty) {
        FrameLayout empty = (FrameLayout) getActivity().findViewById(R.id.empty_layout);
        if (isEmpty) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
    }

}
