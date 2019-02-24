package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.impl.UserActivityDetail;
import com.jenking.spandroid.presenter.UserActivityPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserActiDetailActivity extends BaseActivity {

    private UserActivityDetail userActivityDetail;
    private UserActivityPresenter userActivityPresenter;
    @BindView(R.id.activity_name)
    TextView activity_name;
    @BindView(R.id.activity_time)
    TextView activity_time;
    @BindView(R.id.user_activity_status)
    TextView user_activity_status;
    @BindView(R.id.user_activity_score)
    TextView user_activity_score;
    @BindView(R.id.remark)
    TextView remark;

    @BindView(R.id.manager_bar)
    LinearLayout manager_bar;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify)
    void modify(){
        if (userActivityDetail!=null){
            Intent intent = new Intent(this, UserActiOperateActivity.class);
            intent.putExtra("user_id",userActivityDetail.getUser_id());
            intent.putExtra("model",new Gson().toJson(userActivityDetail));
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
                        if (userActivityPresenter!=null&&userActivityDetail!=null){
                            Map<String,String> params = RS.getBaseParams(UserActiDetailActivity.this);
                            params.put("id",userActivityDetail.getId());
                            userActivityPresenter.deleteUserActivity(params);
                        }
                    }
                }).show();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_acti_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            String json = intent.getStringExtra("model");
            userActivityDetail = new Gson().fromJson(json, UserActivityDetail.class);

            if (userActivityDetail!=null){
                activity_name.setText(userActivityDetail.getActivity_name());
                activity_time.setText(userActivityDetail.getActivity_time());
                user_activity_status.setText(userActivityDetail.getUser_activity_status());
                user_activity_score.setText(userActivityDetail.getUser_activity_score());
                remark.setText(userActivityDetail.getRemark());
            }
        }

        userActivityPresenter = new UserActivityPresenter(this);
        userActivityPresenter.setOnCallBack(new UserActivityPresenter.OnCallBack() {
            @Override
            public void getUserActivityByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void addUserActivity(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserActivity(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserActivity(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(UserActiDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        if (AccountTool.isLogin(this)&&StringUtil.isEquals(AccountTool.getUserType(this),AccountTool.usertype_manager)){
            manager_bar.setVisibility(View.VISIBLE);
        }
    }
    
}
