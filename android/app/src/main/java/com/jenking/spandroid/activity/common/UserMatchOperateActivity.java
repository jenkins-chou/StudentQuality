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
import com.jenking.spandroid.models.base.ActiModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.presenter.ActiPresenter;
import com.jenking.spandroid.presenter.UserMatchPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserMatchOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private UserMatchDetail userMatchDetail;
    private UserMatchPresenter userMatchPresenter;

    private String select_user_id;
    private String select_match_id;
    private String select_match_name;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.match_name)
    TextView match_name;
    @BindView(R.id.user_match_status)
    EditText user_match_status;
    @BindView(R.id.user_match_score)
    EditText user_match_score;
    @BindView(R.id.remark)
    EditText remark;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick({R.id.select_match,R.id.match_name})
    void select_match(){
        Intent intent = new Intent(this,MatchListActivity.class);
        startActivityForResult(intent,MatchListActivity.SelectMatchCode);
    }

    @OnClick(R.id.submit)
    void submit(){
        String user_match_status_str = user_match_status.getText().toString();
        String user_match_score_str = user_match_score.getText().toString();
        if (!StringUtil.isNotEmpty(select_match_id)
                ||!StringUtil.isNotEmpty(select_user_id)
                ||!StringUtil.isNotEmpty(user_match_status_str)
                ||!StringUtil.isNotEmpty(user_match_score_str)){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (userMatchPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("user_id",select_user_id);
                params.put("match_id",select_match_id);
                params.put("user_match_status",user_match_status.getText().toString());
                params.put("user_match_score",user_match_score.getText().toString());
                params.put("remark",remark.getText().toString());
                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    userMatchPresenter.addUserMatch(params);
                }else{
                    if (userMatchDetail!=null){
                        params.put("id",userMatchDetail.getId());
                        userMatchPresenter.updateUserMatch(params);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_match_operate);
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
            userMatchDetail = new Gson().fromJson(json, UserMatchDetail.class);

            if (userMatchDetail!=null){
                match_name.setText(userMatchDetail.getMatch_name());
                user_match_status.setText(userMatchDetail.getUser_match_status());
                user_match_score.setText(userMatchDetail.getUser_match_score());
                remark.setText(userMatchDetail.getRemark());
            }
        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("user_id"))){
            select_user_id= intent.getStringExtra("user_id");
        }

        userMatchPresenter = new UserMatchPresenter(this);
        userMatchPresenter.setOnCallBack(new UserMatchPresenter.OnCallBack() {
            @Override
            public void getUserMatchByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void addUserMatch(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(UserMatchOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(UserMatchOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateUserMatch(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(UserMatchOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void deleteUserMatch(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case MatchListActivity.SelectMatchCode:
                if (data!=null){
                    select_match_id = data.getStringExtra("match_id");
                    select_match_name = data.getStringExtra("match_name");
                    match_name.setText(select_match_name);
                }
                break;
        }
    }
}
