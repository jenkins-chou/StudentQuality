package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.presenter.CollegePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CollegeDetailActivity extends BaseActivity {

    private CollegeModel collegeModel;

    @BindView(R.id.college_name)
    TextView college_name;
    @BindView(R.id.school_name)
    TextView school_name;

    private CollegePresenter collegePresenter;

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
                        if (collegePresenter!=null&&collegeModel!=null){
                            Map<String,String> params = RS.getBaseParams(CollegeDetailActivity.this);
                            params.put("id",collegeModel.getId());
                            collegePresenter.deleteCollege(params);
                        }
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
        collegePresenter = new CollegePresenter(this);
        collegePresenter.setOnCallBack(new CollegePresenter.OnCallBack() {
            @Override
            public void addCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void getCollegeBySchool(boolean isSuccess, Object object) {

            }

            @Override
            public void updateCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteCollege(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(CollegeDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
