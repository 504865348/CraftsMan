package com.joshua.craftsman.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.activity.ask.CraftsAnswerQuestionActivity;
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


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_register)
    Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btn_register.setOnClickListener(this);
    }

    @OnClick(R.id.imageButton)
    public void test() {
        startActivity(new Intent(mBaseActivity, CraftsAnswerQuestionActivity.class));
    }

    @OnClick(R.id.btn_login)
    public void login() {
        Log.d(TAG, "login: " + "connecting");
//        final String username = et_username.getText().toString();
//        String pwd = et_pwd.getText().toString();
        final String username = "18761996926";
        String pwd = "123456";

//        final String username = "gj1";
//        String pwd = "111";
        if (username.isEmpty()) {
            Toast.makeText(mBaseActivity, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.isEmpty()) {
            Toast.makeText(mBaseActivity, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_LOGIN)
                .add("username", username)
                .add("password", pwd)
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseJson = response.body().string();
                Log.d(TAG, "onResponse: " + responseJson);
                try {
                    JSONObject jo = new JSONObject(responseJson);
                    String result = jo.getString("result");
                    switch (result) {
                        case "normal":
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    PrefUtils.setString(mBaseActivity, "type", "normal");
                                    Toast.makeText(mBaseActivity, "登录成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mBaseActivity, MainActivity.class));
                                    finish();
                                }
                            });

                            break;
                        case "craftsman":
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    PrefUtils.setString(mBaseActivity, "type", "craftsman");
                                    Toast.makeText(mBaseActivity, "登录成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mBaseActivity, MainActivity.class));
                                    finish();
                                }
                            });
                            break;
                        default:
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mBaseActivity, "用户名或密码错误，登录失败", Toast.LENGTH_SHORT).show();

                                }
                            });
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(mBaseActivity, RegisterActivity.class));
                break;
        }
    }
}
