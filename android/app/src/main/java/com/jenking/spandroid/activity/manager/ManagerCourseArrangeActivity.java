package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;

import butterknife.OnClick;

public class ManagerCourseArrangeActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.type_class)
    void type_class(){
        Intent intent = new Intent(this,ManagerCourseArrangeTypeClass.class);
        startActivity(intent);
    }

    @OnClick(R.id.type_person)
    void type_person(){
        Intent intent = new Intent(this,ManagerCourseArrangeTypePerson.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_course_arrange);
    }
}
