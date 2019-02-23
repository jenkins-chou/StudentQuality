package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.UserMatchOperateActivity;
import com.jenking.spandroid.tools.StringUtil;

import butterknife.OnClick;

public class ManagerReportListActivity extends BaseActivity {

    private String user_id;
    @OnClick(R.id.add_user_match)
    void add_user_match(){
        if (StringUtil.isNotEmpty(user_id)){
            Intent intent = new Intent(this,UserMatchOperateActivity.class);
            intent.putExtra("user_id",user_id);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report_list);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("user_id"))){
            user_id= intent.getStringExtra("user_id");
        }
    }
}
