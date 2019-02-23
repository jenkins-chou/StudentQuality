package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.UserMatchOperateActivity;

import butterknife.OnClick;

public class ManagerReportListActivity extends BaseActivity {

    @OnClick(R.id.add_user_match)
    void add_user_match(){
        Intent intent = new Intent(this,UserMatchOperateActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report_list);
    }
}
