package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.tools.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassDetailActivity extends BaseActivity {

    private ClassModel classModel;
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
        if (classModel!=null){
            Intent intent = new Intent(this, ClassOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(classModel));
            startActivity(intent);
            finish();
        }

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

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))){
            String json = intent.getStringExtra("model");
            classModel = new Gson().fromJson(json,ClassModel.class);

            if (classModel!=null){
                class_name.setText(classModel.getClass_name());
                class_abstract.setText(classModel.getClass_abstract());
                class_detail.setText(classModel.getClass_detail());
                class_headmaster.setText(classModel.getHeadmaster());
                college_name.setText(classModel.getSchool_name()+classModel.getCollege_name());
            }
        }
    }
}
