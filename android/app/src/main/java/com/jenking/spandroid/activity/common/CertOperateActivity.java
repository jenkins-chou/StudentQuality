package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerCertActivity;
import com.jenking.spandroid.dialog.CommonTipsDialog;

import butterknife.OnClick;

public class CertOperateActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cert_operate);
    }
}
