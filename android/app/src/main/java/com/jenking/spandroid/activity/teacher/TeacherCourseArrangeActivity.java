package com.jenking.spandroid.activity.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;

import butterknife.OnClick;

public class TeacherCourseArrangeActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course_arrange);
    }
}
