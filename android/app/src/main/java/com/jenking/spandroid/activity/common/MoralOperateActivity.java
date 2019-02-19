package com.jenking.spandroid.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;

import butterknife.OnClick;

public class MoralOperateActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moral_operate);
    }
}
