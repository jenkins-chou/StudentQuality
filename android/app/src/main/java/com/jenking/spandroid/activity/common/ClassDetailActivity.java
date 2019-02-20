package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.jenking.spandroid.R;
import com.jenking.spandroid.dialog.CommonTipsDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassDetailActivity extends BaseActivity {

    @BindView(R.id.class_name)
    TextView class_name;
    @BindView(R.id.class_abstract)
    TextView class_abstract;
    @BindView(R.id.class_detail)
    TextView class_detail;
    @BindView(R.id.class_headmaster)
    TextView class_headmaster;
    @BindView(R.id.college_name)
    TextView college_name;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify)
    void modify(){
        Intent intent = new Intent(this, ClassOperateActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.delete)
    void delete(){
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
        setContentView(R.layout.activity_class_detail);
    }
}
