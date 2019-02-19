package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jenking.spandroid.R;
import com.jenking.spandroid.tools.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassOperateActivity extends BaseActivity {

    private Object object;
    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("model"))){
            //表明是修改
            operate_tips.setText("当前操作：修改");
        }else{

            operate_tips.setText("当前操作：新增");
        }
    }
}
