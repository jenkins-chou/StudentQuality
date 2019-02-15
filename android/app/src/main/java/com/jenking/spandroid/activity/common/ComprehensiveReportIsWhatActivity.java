package com.jenking.spandroid.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 什么是综合素质报告
 */
public class ComprehensiveReportIsWhatActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprehensive_report_is_what);
    }
}
