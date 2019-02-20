package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TeacherOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private UserModel userModel;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;

    private UserPresenter userPresenter;


    @BindView(R.id.operate_tips)
    TextView operate_tips;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.realname)
    EditText realname;
    @BindView(R.id.slogan)
    EditText slogan;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.idnum)
    EditText idnum;
    @BindView(R.id.nation)
    EditText nation;
    @BindView(R.id.registered_residence)
    EditText registered_residence;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.useridentify)
    EditText useridentify;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.health)
    EditText health;
    @BindView(R.id.entrance_time)
    EditText entrance_time;
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

    @OnClick(R.id.submit)
    void submit(){
        String name_str = name.getText().toString();
        String pass_str = pass.getText().toString();
        String realname_str = realname.getText().toString();
        if (!StringUtil.isNotEmpty(name_str)
                &&!StringUtil.isNotEmpty(pass_str)
                &&!StringUtil.isNotEmpty(realname_str)
                &&!StringUtil.isNotEmpty(select_college_id)
                &&!StringUtil.isNotEmpty(select_college_name)
                &&!StringUtil.isNotEmpty(select_school_id)
                &&!StringUtil.isNotEmpty(select_school_name)){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

            String json = intent.getStringExtra("model");
            userModel = new Gson().fromJson(json, UserModel.class);

        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {
            @Override
            public void login(boolean isSuccess, Object object) {

            }

            @Override
            public void addUser(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            if (resultModel.getData()!=null&&resultModel.getData().size()>0){
                                List<UserModel> userModels = resultModel.getData();
                                UserModel userModel = userModels.get(0);
                                Toast.makeText(TeacherOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                AccountTool.saveUser(TeacherOperateActivity.this,userModel);
                                finish();
                            }

                        }else{
                            Toast.makeText(TeacherOperateActivity.this, "已存在该用户名", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
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
