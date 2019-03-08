package com.jenking.spandroid.activity.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.CourseDetailActivity;
import com.jenking.spandroid.activity.manager.ManagerCourseActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CoursePresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeacherCourseArrangeActivity extends BaseActivity {

    private List<CourseModel> courseModels;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private CoursePresenter coursePresenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course_arrange);
    }

    @Override
    public void initData() {
        super.initData();
        courseModels = new ArrayList<>();

        baseRecyclerAdapter = new BaseRecyclerAdapter<CourseModel>(this,courseModels,R.layout.activity_teacher_course_arrange_item) {
            @Override
            protected void convert(BaseViewHolder helper, final CourseModel item) {
                helper.setText(R.id.course_name,item.getCourse_name());
                helper.setText(R.id.college_name,item.getSchool_name()+"--"+item.getCollege_name());
                helper.setText(R.id.college_type,item.getCourse_type());
                helper.setText(R.id.college_term,item.getTerm_name());

                TextView publish_score = helper.getView(R.id.publish_score);
                publish_score.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TeacherCourseArrangeActivity.this,TeachScoreOperateActivity.class);
                        intent.putExtra("course_id",item.getId());
                        startActivity(intent);
                    }
                });
            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(TeacherCourseArrangeActivity.this,CourseDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(courseModels.get(position)));
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);


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

            }

            @Override
            public void getAllCourses(boolean isSuccess, Object object) {

            }

            @Override
            public void getCourseSelected(boolean isSuccess, Object object) {

            }

            @Override
            public void getCoursesByTeacher(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        courseModels.clear();
                        courseModels = resultModel.getData()!=null?resultModel.getData():courseModels;
                        baseRecyclerAdapter.setData(courseModels);
                    }
                }
            }
        });

        if (AccountTool.isLogin(this)&&AccountTool.getLoginUser(this)!=null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("teacher_id",AccountTool.getLoginUser(this).getId());
            coursePresenter.getCoursesByTeacher(params);
        }
    }
}
