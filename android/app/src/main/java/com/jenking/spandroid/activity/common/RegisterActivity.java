package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.StringUtil;
import com.jenking.spandroid.ui.CommonLoading;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private UserPresenter userPresenter;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.pass)
    TextView password;
    @BindView(R.id.confirm_pass)
    TextView confirm_pass;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.goto_register)
    void goto_register(){
        String username_str = username.getText().toString();
        String password_str = password.getText().toString();
        String confirm_pass_str = confirm_pass.getText().toString();
        if (!StringUtil.isNotEmpty(username_str)
                ||!StringUtil.isNotEmpty(password_str)
                ||!StringUtil.isNotEmpty(confirm_pass_str)){
            CommonTipsDialog.showTip(this,"温馨提示","请完善信息",false);
            return;
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("name",username_str);
            params.put("pass",password_str);
            userPresenter.addUser(params);
            setLoadingEnable(true);
        }
    }

    @OnClick(R.id.return_login)
    void return_login(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    void setLoadingEnable(boolean enable){
        if (loading==null)return;
        if (enable){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        super.initData();
        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {

            @Override
            public void addUser(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(RegisterActivity.this, "新增成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "已存在该用户名", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
