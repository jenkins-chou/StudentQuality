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
import com.jenking.spandroid.models.impl.UserActivityDetail;
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.presenter.UserActivityPresenter;
import com.jenking.spandroid.presenter.UserCertPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserActiOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private UserActivityDetail userActivityDetail;
    private UserActivityPresenter userActivityPresenter;

    private String select_user_id;
    private String select_activity_id;
    private String select_activity_name;

    @BindView(R.id.activity_name)
    TextView activity_name;
    @BindView(R.id.user_activity_status)
    EditText user_activity_status;
    @BindView(R.id.user_activity_score)
    EditText user_activity_score;
    @BindView(R.id.remark)
    EditText remark;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick({R.id.select_activity,R.id.activity_name})
    void select_activity(){
        Intent intent = new Intent(this,ActiListActivity.class);
        startActivityForResult(intent,ActiListActivity.SelectActiCode);
    }

    @OnClick(R.id.submit)
    void submit(){
        if (!StringUtil.isNotEmpty(select_activity_id)
                ||!StringUtil.isNotEmpty(select_user_id)){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (userActivityPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("user_id",select_user_id);
                params.put("activity_id",select_activity_id);
                params.put("user_activity_status",user_activity_status.getText().toString());
                params.put("user_activity_score",user_activity_score.getText().toString());
                params.put("remark",remark.getText().toString());
                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    userActivityPresenter.addUserActivity(params);
                }else{
                    if (userActivityDetail!=null){
                        params.put("id",userActivityDetail.getId());
                        userActivityPresenter.updateUserActivity(params);
                    }
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_acti_operate);
    }


    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

            String json = intent.getStringExtra("model");
            userActivityDetail = new Gson().fromJson(json, UserActivityDetail.class);

            if (userActivityDetail!=null){
                select_activity_id = userActivityDetail.getActivity_id();
                activity_name.setText(userActivityDetail.getActivity_name());
                user_activity_status.setText(userActivityDetail.getUser_activity_status());
                user_activity_score.setText(userActivityDetail.getUser_activity_score());
                remark.setText(userActivityDetail.getRemark());
            }
        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("user_id"))){
            select_user_id= intent.getStringExtra("user_id");
        }

        userActivityPresenter = new UserActivityPresenter(this);
        userActivityPresenter.setOnCallBack(new UserActivityPresenter.OnCallBack() {
            @Override
            public void getUserActivityByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void addUserActivity(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(UserActiOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(UserActiOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateUserActivity(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(UserActiOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void deleteUserActivity(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ActiListActivity.SelectActiCode:
                if (data!=null){
                    select_activity_id = data.getStringExtra("acti_id");
                    select_activity_name = data.getStringExtra("acti_name");
                    activity_name.setText(select_activity_name);
                }
                break;
        }
    }
}
