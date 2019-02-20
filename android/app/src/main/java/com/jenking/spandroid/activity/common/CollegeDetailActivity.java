package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.tools.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class CollegeDetailActivity extends BaseActivity {

    private CollegeModel collegeModel;

    @BindView(R.id.college_name)
    TextView college_name;
    @BindView(R.id.school_name)
    TextView school_name;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify)
    void modify_match(){
        if (collegeModel!=null) {
            Intent intent = new Intent(this, CollegeOperateActivity.class);
            intent.putExtra("model", new Gson().toJson(collegeModel));
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.delete)
    void delete_match(){
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
        setContentView(R.layout.activity_college_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))){
            //表明是修改
            String json = intent.getStringExtra("model");
            collegeModel = new Gson().fromJson(json,CollegeModel.class);
            if (collegeModel!=null){
                college_name.setText(collegeModel.getCollege_name());
                school_name.setText(collegeModel.getSchool_name());
            }
        }else{
        }
    }
}
