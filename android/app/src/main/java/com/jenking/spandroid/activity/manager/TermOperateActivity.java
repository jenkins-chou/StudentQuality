package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.models.base.TermModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.tools.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TermOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private TermModel termModel;
    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.term_name)
    EditText term_name;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;
            String json = intent.getStringExtra("model");
            termModel = new Gson().fromJson(json, TermModel.class);
            if (termModel!=null){

            }
        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }
    }
}
