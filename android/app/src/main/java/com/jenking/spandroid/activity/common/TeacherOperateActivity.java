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
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.List;
import java.util.Map;

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
                ||!StringUtil.isNotEmpty(pass_str)
                ||!StringUtil.isNotEmpty(realname_str)
                ||!StringUtil.isNotEmpty(select_college_id)
                ||!StringUtil.isNotEmpty(select_college_name)
                ||!StringUtil.isNotEmpty(select_school_id)
                ||!StringUtil.isNotEmpty(select_school_name)){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("name",name_str);
            params.put("pass",pass_str);
            params.put("realname",realname.getText().toString());
            params.put("avatar","");
            params.put("slogan",slogan.getText().toString());
            params.put("sex",sex.getText().toString());
            params.put("age",age.getText().toString());
            params.put("idnum",idnum.getText().toString());
            params.put("nation",nation.getText().toString());
            params.put("registered_residence",registered_residence.getText().toString());
            params.put("email",email.getText().toString());
            params.put("useridentify",useridentify.getText().toString());
            params.put("phone",phone.getText().toString());
            params.put("address",address.getText().toString());
            params.put("health",health.getText().toString());
            params.put("entrance_time",entrance_time.getText().toString());
            params.put("class_id","");
            params.put("college_id",select_college_id);
            params.put("school_id",select_school_id);
            params.put("class_name","");
            params.put("college_name",select_college_name);
            params.put("school_name",select_school_name);
            params.put("type","2");
            userPresenter.addUser(params);
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

            if (userModel!=null){
                Log.e("usermodel",userModel.toString());

                name.setText(userModel.getName());
                pass.setText(userModel.getPass());
                realname.setText(userModel.getRealname());
                slogan.setText(userModel.getSlogan());
                sex.setText(userModel.getSex());
                age.setText(userModel.getAge());
                idnum.setText(userModel.getIdnum());
                nation.setText(userModel.getNation());
                registered_residence.setText(userModel.getRegistered_residence());
                email.setText(userModel.getEmail());
                useridentify.setText(userModel.getUseridentify());
                phone.setText(userModel.getPhone());
                address.setText(userModel.getAddress());
                health.setText(userModel.getHealth());
                entrance_time.setText(userModel.getEntrance_time());
                college_name.setText(userModel.getCollege_name());
            }

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
                            Toast.makeText(TeacherOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(TeacherOperateActivity.this, "已存在该用户名", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachers(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachersByCollege(boolean isSuccess, Object object) {

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
