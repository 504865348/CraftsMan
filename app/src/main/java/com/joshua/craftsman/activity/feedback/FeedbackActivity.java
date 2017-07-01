package com.joshua.craftsman.activity.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.fragment.CraftsInfoFragment;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.feedback_tool_bar)
    Toolbar feedbackToolBar;
    @BindView(R.id.feedback_commit)
    Button feedbackCommit;
    @BindView(R.id.feedback_edit_content)
    EditText edit_content;
    @BindView(R.id.feedback_edit_tel)
    EditText edit_tel;

    public String editContent,editTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        feedbackToolBar.setOnClickListener(this);
        feedbackCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedback_tool_bar:
                startActivity(new Intent(mBaseActivity, CraftsInfoFragment.class));
                break;
            case R.id.feedback_commit:
                putDataToServer();
                break;
        }
    }

    private void putDataToServer() {
        editContent = edit_content.getText().toString();
        editTel = edit_tel.getText().toString();
        if (editContent.isEmpty()) {
            Toast.makeText(mBaseActivity, "反馈意见不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_FEEDBACK)
                .add("content", editContent)
                .add("tel", editTel)
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
                            Toast.makeText(mBaseActivity, "意见反馈成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    startActivity(new Intent(mBaseActivity, CraftsInfoFragment.class));
                    finish();
                } else {
                    Toast.makeText(mBaseActivity, "意见反馈失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected void error() {
                Toast.makeText(mBaseActivity, "意见反馈失败，请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
