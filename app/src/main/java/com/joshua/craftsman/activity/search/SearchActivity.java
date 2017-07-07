package com.joshua.craftsman.activity.search;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joshua.craftsman.R;
import com.joshua.craftsman.utils.RecordSQLiteOpenHelper;
import com.joshua.craftsman.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.search_history_back) ImageView img_back;
    @BindView(R.id.search_interface_search) TextView text_search;
    @BindView(R.id.search_history_edit) EditText mSearchHistoryEdit;
    @BindView(R.id.search_interface_clear) TextView mSearchInterfaceClear;
    @BindView(R.id.search_flow) FlowLayout mSearchFlow;
    @BindView(R.id.search_no_history) TextView mSearchNoHistory;

    // 数据库变量
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ButterKnife.bind(this);

        helper = new RecordSQLiteOpenHelper(SearchActivity.this);
        setListener();
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
                    }
                    // search...
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
     * 插入数据
     * @param tempName 搜索字串
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 检查数据库中是否已经有该条记录
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
