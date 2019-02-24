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
import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.presenter.UserMatchPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserMatchDetailActivity extends BaseActivity {

    private UserMatchDetail userMatchDetail;
    private UserMatchPresenter userMatchPresenter;
    @BindView(R.id.match_name)
    TextView match_name;
    @BindView(R.id.match_time)
    TextView match_time;
    @BindView(R.id.match_level)
    TextView match_level;
    @BindView(R.id.user_match_status)
    TextView user_match_status;
    @BindView(R.id.user_match_score)
    TextView user_match_score;
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
        if (userMatchDetail!=null){
            Intent intent = new Intent(this, UserMatchOperateActivity.class);
            intent.putExtra("user_id",userMatchDetail.getUser_id());
            intent.putExtra("model",new Gson().toJson(userMatchDetail));
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
                        if (userMatchPresenter!=null&&userMatchDetail!=null){
                            Map<String,String> params = RS.getBaseParams(UserMatchDetailActivity.this);
                            params.put("id",userMatchDetail.getId());
                            userMatchPresenter.deleteUserMatch(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_match_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            String json = intent.getStringExtra("model");
            userMatchDetail = new Gson().fromJson(json, UserMatchDetail.class);

            if (userMatchDetail!=null){
                match_name.setText(userMatchDetail.getMatch_name());
                match_time.setText(userMatchDetail.getMatch_time());
                match_level.setText(userMatchDetail.getMatch_level());
                user_match_status.setText(userMatchDetail.getUser_match_status());
                user_match_score.setText(userMatchDetail.getUser_match_score());
                remark.setText(userMatchDetail.getRemark());
            }
        }

        userMatchPresenter = new UserMatchPresenter(this);
        userMatchPresenter.setOnCallBack(new UserMatchPresenter.OnCallBack() {
            @Override
            public void getUserMatchByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void addUserMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserMatch(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(UserMatchDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
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
