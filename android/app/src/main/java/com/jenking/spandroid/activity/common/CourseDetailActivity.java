package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.presenter.CoursePresenter;
import com.jenking.spandroid.tools.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseDetailActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.operate_tips)
    TextView operate_tips;
    @BindView(R.id.course_name)
    TextView course_name;
    @BindView(R.id.course_stunum)
    TextView course_stunum;
    @BindView(R.id.course_abstract)
    TextView course_abstract;
    @BindView(R.id.course_detail)
    TextView course_detail;
    @BindView(R.id.course_type)
    TextView course_type;
    @BindView(R.id.course_status)
    TextView course_status;
    @BindView(R.id.course_score)
    TextView course_score;
    @BindView(R.id.course_term)
    TextView course_term;
    @BindView(R.id.course_teacher)
    TextView course_teacher;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.college_name)
    TextView college_name;
    
    private CourseModel courseModel;
    private CoursePresenter coursePresenter;


    @OnClick(R.id.modify_course)
    void modify_course(){
        if (courseModel!=null){
            Intent intent = new Intent(this,CourseOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(courseModel));
            startActivity(intent);
        }

    }

    @OnClick(R.id.delete_course)
    void delete_course(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            String json = intent.getStringExtra("model");
            courseModel = new Gson().fromJson(json, CourseModel.class);
            if (courseModel!=null){
                course_name.setText(courseModel.getCourse_name());
                course_stunum.setText(courseModel.getCourse_stunum());
                course_abstract.setText(courseModel.getCourse_abstract());
                course_detail.setText(courseModel.getCourse_detail());
                course_type.setText(courseModel.getCourse_type());
                course_status.setText(courseModel.getCourse_status());
                course_score.setText(courseModel.getCourse_score());
                course_term.setText(courseModel.getTerm_name());
                course_teacher.setText(courseModel.getTeacher_name());
                remark.setText(courseModel.getRemark());
                college_name.setText(courseModel.getSchool_name()+"--"+courseModel.getCollege_name());
            }
        }
    }
}
