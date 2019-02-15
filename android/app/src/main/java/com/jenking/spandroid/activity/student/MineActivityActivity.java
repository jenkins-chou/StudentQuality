package com.jenking.spandroid.activity.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.presenter.AAAPresenterTemplate;

import java.util.HashMap;

import butterknife.OnClick;

public class MineActivityActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_activity);
    }

    @Override
    public void initData() {
        super.initData();
        AAAPresenterTemplate aaaPresenterTemplate = new AAAPresenterTemplate(this);
        aaaPresenterTemplate.setOnCallBack(new AAAPresenterTemplate.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        aaaPresenterTemplate.request(new HashMap<String, String>());//示例
    }
}
