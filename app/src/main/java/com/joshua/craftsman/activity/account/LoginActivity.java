package com.joshua.craftsman.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.activity.core.TestActivity;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.http.ResponseInfo;
import com.joshua.craftsman.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_pwd)
    EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.btn_login)
    public void login() {
        Log.d(TAG, "login: " + "connecting");
//        String username = et_username.getText().toString();
//        String pwd = et_pwd.getText().toString();
        final String username = "yuan";
        String pwd = "111";

        OkHttpClient mClient= new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_LOGIN)
                .add("username", username)
                .add("password", pwd)
                .add("type", "normal")
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                Log.d(TAG, "success: "+result);
                PrefUtils.setString(mBaseActivity, "phone", username);
                startActivity(new Intent(mBaseActivity, MainActivity.class));
//                startActivity(new Intent(mBaseActivity, TestActivity.class));

            }

            @Override
            protected void error() {

            }
        });
    }
    }
