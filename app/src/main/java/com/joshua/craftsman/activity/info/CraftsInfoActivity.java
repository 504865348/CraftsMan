package com.joshua.craftsman.activity.info;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CraftsInfoActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.edit_my_info_tool_bar)
    Toolbar editMyInfoToolBar;
    @BindView(R.id.etNickname)
    EditText et_Nickname;
    @BindView(R.id.etIntroduce)
    EditText et_Introduce;
    @BindView(R.id.etSex)
    EditText et_Sex;
    @BindView(R.id.etBirthday)
    EditText et_Birthday;
    @BindView(R.id.etAddress)
    EditText et_Address;
    @BindView(R.id.my_info_commit)
    Button myInfoCommit;

    public String nickName,introduce,sex,birthday,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_edit_myinfo);
        ButterKnife.bind(this);
        initListener();
        editMyInfoToolBar.setTitle("");
        setSupportActionBar(editMyInfoToolBar);

    }
    private void initListener() {
        myInfoCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info_commit:
                putDataToServer();
                break;
        }
    }
    private void putDataToServer() {
        nickName = et_Nickname.getText().toString();
        introduce = et_Introduce.getText().toString();
        sex = et_Sex.getText().toString();
        birthday = et_Birthday.getText().toString();
        address = et_Address.getText().toString();
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();

        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_EDITMYINFO)
                .add("nickName", nickName)
                .add("introduce", introduce)
                .add("sex", sex)
                .add("birthday", birthday)
                .add("address", address)
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
                            Toast.makeText(mBaseActivity, "个人资料编辑成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //showMyInfo();
                    finish();
                } else {
                    Toast.makeText(mBaseActivity, "个人资料编辑失败,请检查网络连接", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected void error() {
                Toast.makeText(mBaseActivity, "个人资料编辑失败,请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });
    }
/*
    private  void showMyInfo() {
        et_Nickname.setText(nickName);
        et_Introduce.setText(introduce);
        et_Sex.setText(sex);
        et_Birthday.setText(birthday);
        et_Address.setText(address);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
