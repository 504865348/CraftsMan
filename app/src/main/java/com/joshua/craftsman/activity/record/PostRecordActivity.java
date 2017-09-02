package com.joshua.craftsman.activity.record;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostRecordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.record_info_title)
    EditText record_info_title;
    @BindView(R.id.record_info_choose_album)
    TextView record_info_choose_album;
    @BindView(R.id.record_info_price)
    EditText record_info_price;
    @BindView(R.id.record_info_intro)
    EditText record_info_intro;
    @BindView(R.id.record_info_tool_bar)
    Toolbar recordInfoToolBar;

    private String mAlbumId;
    private String name;
    private ProgressDialog mDialog;
    private long mDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_info);
        ButterKnife.bind(this);
        record_info_choose_album.setOnClickListener(this);
        name = getIntent().getStringExtra("name");
        Button btn_post = (Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(this);
        recordInfoToolBar.setOnClickListener(this);

    }

    /**
     * 上传至
     */
    public void postToServer() {
        String title = record_info_title.getText().toString();
        String idAlbum = mAlbumId;
        String creater = PrefUtils.getString(mBaseActivity, "phone", "");
        String intro = record_info_intro.getText().toString();
        String path = Environment.getExternalStorageDirectory().getPath() + "/crafts_videos/" + name;
        File file = new File(path);
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(file.getAbsolutePath());
            mDuration = mediaPlayer.getDuration();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream;charset=utf-8"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("record", "record.mp4", fileBody)
                .addFormDataPart("RecordTitle", title)
                .addFormDataPart("Name", creater)
                .addFormDataPart("Special", idAlbum)
                .addFormDataPart("Introduction", intro)
                .addFormDataPart("Duration", mDuration + "")
                .build();
        Request request = new Request.Builder()
                .url(Server.SERVER_RECORD)
                .post(requestBody)
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "createAlbum:fail" + e.getMessage());
                mDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseJson = response.body().string();
                Log.d(TAG, "onResponse: " + responseJson);
                JSONObject jo = null;
                try {
                    jo = new JSONObject(responseJson);
                    String result = jo.getString("result");
                    if (result.equals("true")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PrefUtils.setString(mBaseActivity,name,name);
                                Toast.makeText(mBaseActivity, "节目添加成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mBaseActivity, "节目添加失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    mDialog.dismiss();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.record_info_tool_bar:
                startActivity(new Intent(mBaseActivity, MyRecordActivity.class));
                break;
            case R.id.record_info_choose_album:
                Intent intent = new Intent(this, AlbumListActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_post:
                mDialog = new ProgressDialog(this);
                mDialog.setCancelable(false);
                mDialog.setMessage("视频上传中，请稍后...");
                mDialog.show();
                postToServer();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                String title = data.getStringExtra("albumTitle");
                mAlbumId = data.getStringExtra("albumId");
                record_info_choose_album.setText(title);
                break;
            default:
                break;
        }


    }
}

