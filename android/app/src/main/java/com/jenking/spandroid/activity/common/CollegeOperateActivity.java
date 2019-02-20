package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.models.base.SchoolModel;
import com.jenking.spandroid.presenter.CollegePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CollegeOperateActivity extends BaseActivity {

    @BindView(R.id.college_name)
    EditText college_name;
    @BindView(R.id.school_name)
    TextView school_name;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    private String select_school_id;
    private String select_school_name;

    private boolean isAddData = true;//判断是否是新增
    private CollegeModel collegeModel;

    private CollegePresenter collegePresenter;

    @OnClick(R.id.school_name)
    void setSelect_school_name(){
        Intent intent = new Intent(this,SchoolListActivity.class);
        startActivityForResult(intent,SchoolListActivity.SelectSchoolCode);
    }

    @OnClick(R.id.submit)
    void submit(){
        String college_name_str = college_name.getText().toString();
        if (!StringUtil.isNotEmpty(college_name_str)
                ||!StringUtil.isNotEmpty(select_school_id)){
            Toast.makeText(this, "请完善学院信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (collegePresenter!=null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("college_name",college_name_str);
            params.put("school_id",select_school_id);
            params.put("school_name",select_school_name);
            if (isAddData){
                collegePresenter.addCollege(params);
            }else{

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))){
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;
            String json = intent.getStringExtra("model");
            collegeModel = new Gson().fromJson(json,CollegeModel.class);
            if (collegeModel!=null){

            }

        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
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
            public void updateCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteCollege(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("requestCode",requestCode+"");
        Log.e("resultCode",resultCode+"");
        switch (requestCode){
            case SchoolListActivity.SelectSchoolCode:
                if (data!=null){
                    select_school_id = data.getStringExtra("school_id");
                    select_school_name= data.getStringExtra("school_name");
                    school_name.setText(select_school_name);
                }
                break;
        }
    }
}
