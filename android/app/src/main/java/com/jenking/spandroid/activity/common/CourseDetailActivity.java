package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.presenter.CoursePresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseDetailActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

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

    @BindView(R.id.manager_bar)
    LinearLayout manager_bar;
    
    private CourseModel courseModel;
    private CoursePresenter coursePresenter;


    @OnClick(R.id.modify_course)
    void modify_course(){
        if (courseModel!=null){
            Intent intent = new Intent(this,CourseOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(courseModel));
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.delete_course)
    void delete_course(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (courseModel!=null&&coursePresenter!=null){
                            Map<String,String> params = RS.getBaseParams(CourseDetailActivity.this);
                            params.put("id",courseModel.getId());
                            coursePresenter.deleteCourse(params);
                        }
                    }
                }).show();
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

        coursePresenter = new CoursePresenter(this);
        coursePresenter.setOnCallBack(new CoursePresenter.OnCallBack() {
            @Override
            public void addCourse(boolean isSuccess, Object object) {

            }

            @Override
            public void updateCourse(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteCourse(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(CourseDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void getAllCourses(boolean isSuccess, Object object) {

            }

            @Override
            public void getCourseSelected(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        if (AccountTool.isLogin(this)&&StringUtil.isEquals(AccountTool.getUserType(this),AccountTool.usertype_manager)){
            manager_bar.setVisibility(View.VISIBLE);
        }
    }
}
