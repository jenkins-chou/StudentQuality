package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerTermActivity;
import com.jenking.spandroid.models.base.TermModel;

import butterknife.OnClick;

public class TermDetailActivity extends BaseActivity {

    private TermModel termModel;
    @OnClick(R.id.modify_term)
    void modify_term(){
        Intent intent = new Intent(this,TermDetailActivity.class);
//        intent.putExtra("model",new Gson().toJson(datas.get(position)));
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
    }
}
