package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.jenking.spandroid.R;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.StringUtil;
import com.jenking.spandroid.ui.CommonLoading;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private UserPresenter userPresenter;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.forget_password)
    void forget_password(){
        CommonTipsDialog.showTip(this,"温馨提示","忘记登录密码或账号请联系管理员找回",false);
    }

    @OnClick(R.id.goto_register)
    void goto_register(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.goto_login)
    void goto_login(){
        String name_str = name.getText().toString();
        String pass_str = pass.getText().toString();
        if (!StringUtil.isNotEmpty(name_str)
                &&!StringUtil.isNotEmpty(pass_str)){
            CommonTipsDialog.showTip(this,"温馨提示","请完善信息",false);
            return;
        }else{

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
