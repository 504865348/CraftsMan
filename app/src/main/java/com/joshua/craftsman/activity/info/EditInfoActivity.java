package com.joshua.craftsman.activity.info;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.utils.myinfoCityAndDate.ChooseCityInterface;
import com.joshua.craftsman.utils.myinfoCityAndDate.ChooseCityUtil;
import com.joshua.craftsman.utils.myinfoCityAndDate.ChooseDateInterface;
import com.joshua.craftsman.utils.myinfoCityAndDate.ChooseDateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EditInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.edit_my_info_tool_bar)
    Toolbar editMyInfoToolBar;
    @BindView(R.id.etNickname)
    EditText et_Nickname;
    @BindView(R.id.etIntroduce)
    EditText et_Introduce;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.my_info_commit)
    Button myInfoCommit;
    @BindView(R.id.tvAddress)
    TextView tvAddress;

    public String nickName, introduce;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_edit_myinfo);
        ButterKnife.bind(this);
        initListener();
        editMyInfoToolBar.setTitle("");
        setSupportActionBar(editMyInfoToolBar);
        showInfo();
    }

    private void initListener() {
        tvSex.setOnClickListener(this);
        tvBirthday.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        myInfoCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etNickname:
                setNickName();
                break;
            case R.id.etIntroduce:
                setIntroduce();
                break;
            case R.id.tvSex:
                setSex();
                break;
            case R.id.tvBirthday:
                setBirthday();
                break;
            case R.id.tvAddress:
                setAddress();
                break;
            case R.id.my_info_commit:
                saveInfo();
                break;
        }
    }

    private void setNickName() {
        nickName = et_Nickname.getText().toString();
    }

    private void setIntroduce() {
        introduce = et_Introduce.getText().toString();
    }

    private void setSex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mBaseActivity);
        builder.setTitle("设置性别");
        final String[] items = {"男", "女", "保密"};
        int i;
        for (i = 0; i < items.length; i++) {
            if (items[i].equals(tvSex.getText())) {
                break;
            }
        }
        builder.setSingleChoiceItems(items, i, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvSex.setText(items[which].toString());
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setBirthday() {
        final ChooseDateUtil dateUtil = new ChooseDateUtil();
        int[] oldDateArray = {2017, 01, 01};
        dateUtil.createDialog(this, oldDateArray, new ChooseDateInterface() {
            @Override
            public void sure(int[] newDateArray) {
                tvBirthday.setText(newDateArray[0] + "年" + newDateArray[1] + "月" + newDateArray[2] + "日");
                tvBirthday.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    private void setAddress() {
        final ChooseCityUtil cityUtil = new ChooseCityUtil();
        String[] oldCityArray = {"江苏", "镇江", "京口"};
        cityUtil.createDialog(this, oldCityArray, new ChooseCityInterface() {
            @Override
            public void sure(String[] newCityArray) {
                tvAddress.setText(newCityArray[0] + "-" + newCityArray[1] + "-" + newCityArray[2]);
            }
        });
    }

    private void saveInfo() {
        sp = getSharedPreferences("CraftsmanUserInfo.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nickName", et_Nickname.getText().toString());
        editor.putString("introduce", et_Introduce.getText().toString());
        editor.putString("sex", tvSex.getText().toString());
        editor.putString("birthday", tvBirthday.getText().toString());
        editor.putString("address", tvAddress.getText().toString());
        editor.commit();
        Toast.makeText(getBaseContext(), "保存成功", Toast.LENGTH_SHORT).show();
        //putDataToServer();
    }

    /*private void putDataToServer() {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(getApplicationContext()))
                .build();

        RequestBody params = new FormBody.Builder()
                .add("method", Server.EDIT_MY_INFO)
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
    }*/

    private void showInfo() {
        sp = getSharedPreferences("CraftsmanUserInfo.txt", Context.MODE_PRIVATE);
        et_Nickname.setText(sp.getString("nickName", ""));
        et_Introduce.setText(sp.getString("introduce", ""));
        tvSex.setText(sp.getString("sex", ""));
        tvBirthday.setText(sp.getString("birthday", ""));
        tvAddress.setText(sp.getString("address", ""));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
