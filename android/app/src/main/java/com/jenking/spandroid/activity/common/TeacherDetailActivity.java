package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeacherDetailActivity extends BaseActivity {

    private UserPresenter userPresenter;
    private UserModel userModel;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.pass)
    TextView pass;
    @BindView(R.id.realname)
    TextView realname;
    @BindView(R.id.slogan)
    TextView slogan;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.idnum)
    TextView idnum;
    @BindView(R.id.nation)
    TextView nation;
    @BindView(R.id.registered_residence)
    TextView registered_residence;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.useridentify)
    TextView useridentify;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.health)
    TextView health;
    @BindView(R.id.entrance_time)
    TextView entrance_time;
    @BindView(R.id.college_name)
    TextView college_name;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify_teacher)
    void modify_match(){
        if (userModel!=null){
            Intent intent = new Intent(this, TeacherOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(userModel));
            startActivity(intent);
        }

    }

    @OnClick(R.id.delete_teacher)
    void delete_match(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (userModel!=null&&userPresenter!=null){
                            Map<String,String> params = RS.getBaseParams(TeacherDetailActivity.this);
                            params.put("id",userModel.getId());
                            userPresenter.deleteTeacher(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
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

        }

        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {
            @Override
            public void login(boolean isSuccess, Object object) {

            }

            @Override
            public void addUser(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(TeacherDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void getTeachers(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachersByCollege(boolean isSuccess, Object object) {

            }
        });
    }
}
