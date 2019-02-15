package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;

import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @OnClick(R.id.goto_register)
    void goto_register(){
//        Intent intent = new Intent(this,RegisterActivity.class);
//        startActivity(intent);
//        finish();
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
}
