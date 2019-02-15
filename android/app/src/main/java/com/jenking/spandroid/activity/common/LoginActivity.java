package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.dialog.CommonTipsDialog;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
