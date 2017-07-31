package com.joshua.craftsman.activity.search;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joshua.craftsman.R;
import com.joshua.craftsman.activity.core.BaseActivity;
import com.joshua.craftsman.activity.record.AlbumAdapter;
import com.joshua.craftsman.adapter.HotCraftsAdapter;
import com.joshua.craftsman.adapter.QuesAnsClassifyAdapter;
import com.joshua.craftsman.entity.Album;
import com.joshua.craftsman.entity.Craftsman;
import com.joshua.craftsman.entity.HotCraftsman;
import com.joshua.craftsman.entity.QuesAnsClassify;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;
import com.joshua.craftsman.utils.RecordSQLiteOpenHelper;
import com.joshua.craftsman.view.FlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.joshua.craftsman.R.id.album_list_lv;
import static com.joshua.craftsman.R.id.hot_crafts_rv;
import static com.joshua.craftsman.R.id.q_a_list_view_examples;
import static com.joshua.craftsman.R.id.question;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.search_history_back)
    ImageView img_back;
    @BindView(R.id.search_interface_search)
    TextView text_search;
    @BindView(R.id.search_history_edit)
    EditText mSearchHistoryEdit;
    @BindView(R.id.search_interface_clear)
    TextView mSearchInterfaceClear;
    @BindView(R.id.search_flow)
    FlowLayout mSearchFlow;
    @BindView(R.id.search_no_history)
    TextView mSearchNoHistory;

    @BindView(R.id.rv_album)
    RecyclerView rv_album;
    @BindView(R.id.tv_album)
    TextView tv_album;
    @BindView(R.id.rv_craftsman)
    RecyclerView rv_craftsman;
    @BindView(R.id.tv_craftsman)
    TextView tv_craftsman;
    @BindView(R.id.rv_question)
    RecyclerView rv_question;
    @BindView(R.id.tv_question)
    TextView tv_question;

    // 数据库变量
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    //访问网络
    private OkHttpClient mClient;
    private List<QuesAnsClassify> list_question=new ArrayList<>();
    private List<Album> list_album=new ArrayList<>();
    private List<HotCraftsman> list_craftsman=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ButterKnife.bind(this);
        helper = new RecordSQLiteOpenHelper(SearchActivity.this);
        setListener();
        refreshData();
    }

    /**
     * 设置点击监听器
     */
    private void setListener() {
        img_back.setOnClickListener(this);
        text_search.setOnClickListener(this);
        mSearchInterfaceClear.setOnClickListener(this);
    }

    /**
     * 加载搜索历史
     * 若不为空，显示流式布局
     * 若为空，显示“无搜索历史”
     */
    private void refreshData() {
        mSearchFlow.removeAllViews(); //清空所有子 View
        // 重新查询并加载搜索历史
        List<String> list = getAllData();
        if (list != null && list.size() > 0) {
            mSearchNoHistory.setVisibility(View.GONE);
            for (String data : list) {
                final TextView textView = (TextView) LayoutInflater.from(this)
                        .inflate(R.layout.flow_text_normal, mSearchFlow, false);
                textView.setText(data);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSearchHistoryEdit.setText(textView.getText());
                    }
                });
                mSearchFlow.addView(textView);
            }
        } else {
            mSearchNoHistory.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_history_back:
                finish();
                break;
            case R.id.search_interface_search:
                // 保存关键字
                String keyWord = mSearchHistoryEdit.getText().toString();
                if (keyWord.length() <= 10) {
                    if (!hasData(keyWord)) {
                        insertData(mSearchHistoryEdit.getText().toString());
                        refreshData();
                        searchFromServer(keyWord);
                    }
                } else {
                    Toast.makeText(this, "关键字过长", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.search_interface_clear:
                deleteData();
                refreshData();
                break;
        }
    }

    /**
     * 搜索
     */
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private void searchFromServer(String keyWord) {
        mClient = new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar(mBaseActivity))
                .build();
        String requestStr="method="+Server.SERVER_SEARCH+"&keyWord="+keyWord;
//        RequestBody params = new FormBody.Builder()
//                .add("method", Server.SERVER_SEARCH)
//                .add("keyWord", keyWord)
//                .build();
        RequestBody params =RequestBody.create(MEDIA_TYPE_MARKDOWN, requestStr);

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(mBaseActivity) {
            @Override
            protected void success(String result) {
                Log.d(TAG, "success:>>>>>>>>>>>>>>>>>>>>>> "+result);
//                parseData(result);
            }

            @Override
            protected void error() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mBaseActivity, "网络错误，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void parseData(String result) {
        try {
            JSONObject jsonObject=new JSONObject(result);
            String question=jsonObject.getString("question");
            String album=jsonObject.getString("album");
            String craftsman=jsonObject.getString("craftsman");
            Gson gson = new Gson();
            //解析question
            list_question = gson.fromJson(question, new TypeToken<List<QuesAnsClassify>>() {
            }.getType());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initRecycleQuestion();
                }
            });
            //解析album
            list_album = gson.fromJson(album, new TypeToken<List<Album>>() {
            }.getType());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initRecycleAlbum();
                }
            });
            //解析craftsman
            list_craftsman = gson.fromJson(craftsman, new TypeToken<List<HotCraftsman>>() {
            }.getType());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initRecycleCraftsman();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initRecycleCraftsman() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_craftsman.setLayoutManager(linearLayoutManager);
        rv_craftsman.setAdapter(new HotCraftsAdapter(this, list_craftsman));
    }

    private void initRecycleAlbum() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rv_album.setLayoutManager(linearLayoutManager);
        rv_album.setHasFixedSize(true);
        rv_album.setNestedScrollingEnabled(false);
        AlbumAdapter albumAdapter = new AlbumAdapter(this, list_album);
        rv_album.setAdapter(albumAdapter);
    }

    private void initRecycleQuestion() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_question.setLayoutManager(linearLayoutManager);
        rv_question.setHasFixedSize(true);
        rv_question.setNestedScrollingEnabled(false);
        rv_question.setAdapter(new QuesAnsClassifyAdapter(this, list_question));
    }

    /**
     * 插入数据
     *
     * @param tempName 搜索字串
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 检查数据库中是否已经有该条记录
     *
     * @param tempName 关键字
     * @return boolean：该关键字是否已经存在
     */
    private boolean hasData(String tempName) {
        boolean isExist = false;
        // 从 Record 这个表里找到 name = tempName 的 id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        if (cursor.moveToNext()) {
            isExist = true;
        }
        cursor.close();
        return isExist;
    }

    /**
     * 获取所有搜索历史的字串
     *
     * @return 搜索字串的集合 List<String>
     */
    private List<String> getAllData() {
        List<String> list = new ArrayList<>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("records", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            list.add(name);
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

}
