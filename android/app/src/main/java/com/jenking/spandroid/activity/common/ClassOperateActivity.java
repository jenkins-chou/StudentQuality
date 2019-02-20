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
import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.ClassPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassOperateActivity extends BaseActivity {

    private Object object;
    private boolean isAddData = true;
    private ClassModel classModel;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.class_name)
    EditText class_name;
    @BindView(R.id.class_abstract)
    EditText class_abstract;
    @BindView(R.id.class_detail)
    EditText class_detail;
    @BindView(R.id.class_headmaster)
    EditText class_headmaster;
    @BindView(R.id.college_name)
    TextView college_name;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.college_name)
    void college_name(){
        Intent intent = new Intent(this,CollegeListActivity.class);
        startActivityForResult(intent,CollegeListActivity.SelectCollegeCode);
    }

    private ClassPresenter classPresenter;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;

    @OnClick(R.id.submit)
    void submit(){
        String class_name_str = class_name.getText().toString();
        String class_abstract_str = class_abstract.getText().toString();
        String class_detail_str = class_detail.getText().toString();
        String class_headmaster_str = class_headmaster.getText().toString();
        if (!StringUtil.isNotEmpty(class_name_str)
            ||!StringUtil.isNotEmpty(class_abstract_str)
            ||!StringUtil.isNotEmpty(class_detail_str)
            ||!StringUtil.isNotEmpty(class_headmaster_str)
                ||!StringUtil.isNotEmpty(select_college_id)
                ||!StringUtil.isNotEmpty(select_college_name)
                ||!StringUtil.isNotEmpty(select_school_id)
                ||!StringUtil.isNotEmpty(select_school_name)){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (classPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("class_name",class_name_str);
                params.put("class_abstract",class_abstract_str);
                params.put("class_detail",class_detail_str);
                params.put("headmaster",class_headmaster_str);

                params.put("school_id",select_school_id);
                params.put("school_name",select_school_name);
                params.put("college_id",select_college_id);
                params.put("college_name",select_college_name);
                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    classPresenter.addClass(params);
                }else{
                    params.put("id",classModel.getId());
                    classPresenter.updateClass(params);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("model"))){
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

            String json = intent.getStringExtra("model");
            classModel = new Gson().fromJson(json,ClassModel.class);

            if (classModel!=null){
                select_college_id = classModel.getCollege_id();
                select_college_name = classModel.getCollege_name();
                select_school_name = classModel.getSchool_name();
                select_school_id = classModel.getSchool_id();
                class_name.setText(classModel.getClass_name());
                class_abstract.setText(classModel.getClass_abstract());
                class_detail.setText(classModel.getClass_detail());
                class_headmaster.setText(classModel.getHeadmaster());
                college_name.setText(classModel.getSchool_name()+classModel.getCollege_name());
            }

        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        classPresenter = new ClassPresenter(this);
        classPresenter.setOnCallBack(new ClassPresenter.OnCallBack() {
            @Override
            public void addClass(boolean isSuccess, Object object) {
                Log.e("addSchool",""+isSuccess);
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(ClassOperateActivity.this, "新增成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(ClassOperateActivity.this, "不能添加重复名称的班级", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateClass(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(ClassOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void deleteClass(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllClass(boolean isSuccess, Object object) {

            }

            @Override
            public void getClassByCollege(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CollegeListActivity.SelectCollegeCode:
                if (data!=null){
                    select_school_id = data.getStringExtra("school_id");
                    select_school_name= data.getStringExtra("school_name");
                    select_college_id = data.getStringExtra("college_id");
                    select_college_name= data.getStringExtra("college_name");
                    college_name.setText(select_school_name+select_college_name);
                }
                break;
        }
    }
}
