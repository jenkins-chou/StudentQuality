package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.CoursePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private CourseModel courseModel;
    private CoursePresenter coursePresenter;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;
    private String select_term_id;
    private String select_term_name;
    private String select_teacher_id;
    private String select_teacher_name;

    @BindView(R.id.operate_tips)
    TextView operate_tips;
    @BindView(R.id.course_name)
    EditText course_name;
    @BindView(R.id.course_stunum)
    EditText course_stunum;
    @BindView(R.id.course_abstract)
    EditText course_abstract;
    @BindView(R.id.course_detail)
    EditText course_detail;
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
    EditText remark;
    @BindView(R.id.college_name)
    TextView college_name;

    @OnClick(R.id.college_name)
    void college_name(){
        Intent intent = new Intent(this,CollegeListActivity.class);
        startActivityForResult(intent,CollegeListActivity.SelectCollegeCode);
    }

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        String course_name_str = course_name.getText().toString();
        String course_type_str = course_type.getText().toString();
        String course_status_str = course_status.getText().toString();
        if (!StringUtil.isNotEmpty(course_name_str)
                ||!StringUtil.isNotEmpty(course_type_str)
                ||!StringUtil.isNotEmpty(course_status_str)
                ||!StringUtil.isNotEmpty(select_term_id)
                ||!StringUtil.isNotEmpty(select_teacher_id)
                ||!StringUtil.isNotEmpty(select_college_id)
                ||!StringUtil.isNotEmpty(select_school_id)){
            Toast.makeText(this, "请完善必填信息", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (coursePresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("course_name",course_name.getText().toString());
                params.put("course_stunum",course_stunum.getText().toString());
                params.put("course_abstract",course_abstract.getText().toString());
                params.put("course_detail",course_detail.getText().toString());
                params.put("course_type",course_type.getText().toString());
                params.put("course_status",course_status.getText().toString());
                params.put("course_score",course_score.getText().toString());
                params.put("school_id",select_school_id);
                params.put("college_id",select_college_id);
                params.put("term_id",select_term_id);
                params.put("teacher_id",select_teacher_id);
                params.put("school_name",select_school_name);
                params.put("college_name",select_college_name);
                params.put("term_name",select_term_name);
                params.put("teacher_name",select_teacher_name);
                params.put("remark",remark.getText().toString());
                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    coursePresenter.addCourse(params);
                }else{
                    if (courseModel!=null){
                        params.put("is",courseModel.getId());
                        coursePresenter.updateCourse(params);
                    }
                }
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_operate);
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

        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        coursePresenter = new CoursePresenter(this);
        coursePresenter.setOnCallBack(new CoursePresenter.OnCallBack() {
            @Override
            public void addCourse(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(CourseOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(CourseOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateCourse(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteCourse(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllCourses(boolean isSuccess, Object object) {

            }
        });
    }
}
