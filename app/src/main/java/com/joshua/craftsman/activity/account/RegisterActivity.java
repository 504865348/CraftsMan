package com.joshua.craftsman.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.utils.MyUtils;
import com.joshua.craftsman.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.x;
import static com.joshua.craftsman.R.id.cancel_action;
import static com.joshua.craftsman.R.id.et_username;
import static com.joshua.craftsman.R.id.iv_right;
import static okhttp3.Protocol.get;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_sms)
    EditText et_sms;
    @BindView(R.id.btn_send_sms)
    Button btn_send_sms;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.tv_err)
    TextView tv_err;
    @BindView(R.id.cb_agree)
    CheckBox cb_agree;
    ImageView iv_left;
    @BindView(R.id.tv_middle)
    TextView tv_middle;
    @BindView(R.id.iv_right)
    ImageView iv_right;


    private String username = "";
    private String mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initToolBar();
        initListener();
    }

    private void initListener() {
        btn_send_sms.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private void initToolBar() {
        iv_left.setOnClickListener(this);
        iv_right.setVisibility(View.INVISIBLE);
        tv_middle.setText("注册");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_sms:
                username = et_username.getText().toString();
                if (MyUtils.isMobileNO(username)) {
                    sendCheckCode();
                } else {
                    showError("手机号码错误");
                }
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {
        if (cb_agree.isChecked()) {
            if (mCode.equals(et_sms.getText().toString())) {
                String password = et_password.getText().toString();
                if (!password.isEmpty() && password.length() >= 6 && password.length() <= 20) {
                    OkHttpClient mClient = new OkHttpClient.Builder()
                            .cookieJar(new HttpCookieJar(getApplicationContext()))
                            .build();
                    RequestBody params = new FormBody.Builder()
                            .add("method", Server.SERVER_REGISTER)
                            .add("username", username)
                            .add("password", password)
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
                            Log.d(TAG, "success: " + result);
                            if (result.equals("true")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mBaseActivity, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                startActivity(new Intent(mBaseActivity, LoginActivity.class));
                                finish();
                            } else {
                                showError("注册失败");
                            }
                        }

                        @Override
                        protected void error() {
                            showError("注册失败");
                        }
                    });
                } else {
                    showError("密码需要6-20位");
                }

            } else {
                showError("验证码错误");
            }
        } else {
            showError("请阅读并同意工匠注册协议");
        }

    }

    /**
     * 发送短信验证码
     */
    private void sendCheckCode() {
        mCode = MyUtils.generifyCode();
        username = et_username.getText().toString();
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_SMS)
                .add("tel", username)
                .add("code", mCode)
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
                try {
                JSONObject jo = new JSONObject(responseJson);
                String result = jo.getString("result");
                if (result.equals("true")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mBaseActivity, "验证码发送成功", Toast.LENGTH_SHORT).show();
                            TimeCount time = new TimeCount(60000, 1000);
                            time.start();
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mBaseActivity, "验证码发送失败，请稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        });
    }

    /**
     * 发送验证码btn显示倒计时
     */
    private class TimeCount extends CountDownTimer {
        TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            btn_send_sms.setText("重新发送");
            btn_send_sms.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            btn_send_sms.setClickable(false);
            btn_send_sms.setText(millisUntilFinished / 1000 + "");
        }
    }

    /**
     * 显示错误
     *
     * @param err 错误信息
     */
    private void showError(String err) {
        tv_err.setVisibility(View.VISIBLE);
        tv_err.setText(err);
    }

    // 监听返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
