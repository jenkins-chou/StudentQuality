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
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.models.impl.UserMoralDetail;
import com.jenking.spandroid.presenter.UserMoralPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserMoralDetailActivity extends BaseActivity {

    private String select_user_id;
    private UserMoralDetail userMoralDetail;
    private UserMoralPresenter userMoralPresenter;


    @BindView(R.id.moral_name)
    TextView moral_name;
    @BindView(R.id.remark)
    TextView remark;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify)
    void modify(){
        if (userMoralDetail!=null){
            Intent intent = new Intent(this, UserMoralOperateActivity.class);
            intent.putExtra("user_id",userMoralDetail.getUser_id());
            intent.putExtra("model",new Gson().toJson(userMoralDetail));
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
                        if (userMoralPresenter!=null&&userMoralDetail!=null){
                            Map<String,String> params = RS.getBaseParams(UserMoralDetailActivity.this);
                            params.put("id",userMoralDetail.getId());
                            userMoralPresenter.deleteUserMoral(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_moral_detail);
    }
    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent != null && StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改

            String json = intent.getStringExtra("model");
            userMoralDetail = new Gson().fromJson(json, UserMoralDetail.class);

            if (userMoralDetail != null) {
                moral_name.setText(userMoralDetail.getMoral_name());
                remark.setText(userMoralDetail.getRemark());
            }
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

            }

            @Override
            public void updateUserMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserMoral(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(UserMoralDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

}
