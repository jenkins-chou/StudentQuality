package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerSchoolOperateActivity;
import com.jenking.spandroid.dialog.CommonTipsDialog;

import butterknife.OnClick;

public class SchoolDetailActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify_school)
    void modify_school(){
        Intent intent = new Intent(this, ManagerSchoolOperateActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.delete_school)
    void delete_school(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {

                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
    }
}
