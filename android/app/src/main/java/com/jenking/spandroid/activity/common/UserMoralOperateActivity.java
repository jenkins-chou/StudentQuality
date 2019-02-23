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
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.models.impl.UserMoralDetail;
import com.jenking.spandroid.presenter.UserCertPresenter;
import com.jenking.spandroid.presenter.UserMoralPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserMoralOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private UserMoralDetail userMoralDetail;
    private UserMoralPresenter userMoralPresenter;

    private String select_user_id;
    private String select_moral_id;
    private String select_moral_name;

    @BindView(R.id.moral_name)
    TextView moral_name;
    @BindView(R.id.remark)
    EditText remark;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick({R.id.select_moral,R.id.moral_name})
    void select_match(){
        Intent intent = new Intent(this,MoralListActivity.class);
        startActivityForResult(intent,MoralListActivity.SelectMoralCode);
    }

    @OnClick(R.id.submit)
    void submit(){
        if (!StringUtil.isNotEmpty(select_moral_id)
                ||!StringUtil.isNotEmpty(select_user_id)){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (userMoralPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("user_id",select_user_id);
                params.put("moral_id",select_moral_id);
                params.put("create_time",StringUtil.getTime());
                params.put("remark",remark.getText().toString());
                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    userMoralPresenter.addUserMoral(params);
                }else{
                    if (userMoralDetail!=null){
                        params.put("id",userMoralDetail.getId());
                        userMoralPresenter.updateUserMoral(params);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_moral_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent != null && StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

            String json = intent.getStringExtra("model");
            userMoralDetail = new Gson().fromJson(json, UserMoralDetail.class);

            if (userMoralDetail != null) {
                select_moral_id = userMoralDetail.getMoral_id();
                moral_name.setText(userMoralDetail.getMoral_name());
                remark.setText(userMoralDetail.getRemark());
            }
        } else {
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        if (intent != null && StringUtil.isNotEmpty(intent.getStringExtra("user_id"))) {
            select_user_id = intent.getStringExtra("user_id");
        }

        userMoralPresenter = new UserMoralPresenter(this);
        userMoralPresenter.setOnCallBack(new UserMoralPresenter.OnCallBack() {
            @Override
            public void getUserMoralByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void addUserMoral(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(UserMoralOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(UserMoralOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateUserMoral(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(UserMoralOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void deleteUserMoral(boolean isSuccess, Object object) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MoralListActivity.SelectMoralCode:
                if (data!=null){
                    select_moral_id = data.getStringExtra("moral_id");
                    select_moral_name = data.getStringExtra("moral_name");
                    moral_name.setText(select_moral_name);
                }
                break;
        }
    }
    
}
