package com.joshua.craftsman.activity.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.adapter.info.OrderAdapter;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.entity.joshua.Order;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.rv_order)
    RecyclerView rv_order;
    private OkHttpClient mClient;
    private List<Order> list_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order);
        ButterKnife.bind(this);
        getDataFromServer();
    }


    private void getDataFromServer() {
        mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(mBaseActivity))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_QUERY_ORDER)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(mBaseActivity) {
            @Override
            protected void success(String result) {
                parseOrder(result);
            }

            @Override
            protected void error() {

            }
        });

    }

    private void parseOrder(String result) {
        Gson gson = new Gson();
        list_order = gson.fromJson(result, new TypeToken<List<Order>>() {
        }.getType());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecycle();
            }
        });
    }

    private void initRecycle() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mBaseActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_order.setLayoutManager(linearLayoutManager);
        rv_order.setAdapter(new OrderAdapter(mBaseActivity, list_order));
    }
}
