package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerCourseActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CoursePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseListActivity extends BaseActivity {

    public static final int SelectCourseCode = 60002;
    @BindView(R.id.college_name)
    TextView college_name;
    @BindView(R.id.term_name)
    TextView term_name;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;
    private String select_term_id;
    private String select_term_name;

    private List<CourseModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    private CoursePresenter coursePresenter;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.select_college)
    void select_college(){
        Intent intent = new Intent(this,CollegeListActivity.class);
        startActivityForResult(intent,CollegeListActivity.SelectCollegeCode);
    }

    @OnClick(R.id.select_term)
    void select_term(){
        Intent intent = new Intent(this,TermListActivity.class);
        startActivityForResult(intent,TermListActivity.SelectTermCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
    }

    @Override
    public void initData() {
        super.initData();

        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<CourseModel>(this,datas,R.layout.activity_manager_course_item) {
            @Override
            protected void convert(BaseViewHolder helper, CourseModel item) {
                helper.setText(R.id.course_name,item.getCourse_name());
                helper.setText(R.id.college_name,item.getSchool_name()+"--"+item.getCollege_name());
                helper.setText(R.id.college_type,item.getCourse_type());
                helper.setText(R.id.college_term,item.getTerm_name());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("course_id",datas.get(position).getId());
                intent.putExtra("course_name",datas.get(position).getCourse_name());
                intent.putExtra("course_type",datas.get(position).getCourse_type());
                setResult(SelectCourseCode,intent);
                finish();
            }
        });

        baseRecyclerAdapter.openLoadAnimation(false);
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
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas.clear();
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void getCourseSelected(boolean isSuccess, Object object) {
                Log.e("getCourseSelected","getCourseSelected");
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas.clear();
                        datas = resultModel.getData()!=null?resultModel.getData():datas;

                        Log.e("getCourseSelected",datas.size()+"");
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void getCoursesByTeacher(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (coursePresenter!=null){
            if (!StringUtil.isNotEmpty(select_college_id)
                    &&!StringUtil.isNotEmpty(select_school_id)
                    &&!StringUtil.isNotEmpty(select_term_id)){
                coursePresenter.getAllCourses(RS.getBaseParams(this));
            }else {
                Map<String,String> params = RS.getBaseParams(this);
                if (StringUtil.isNotEmpty(select_school_id)&&StringUtil.isNotEmpty(select_college_id)){
                    params.put("school_id",select_school_id);
                    params.put("college_id",select_college_id);
                }
                if (StringUtil.isNotEmpty(select_term_id)){
                    params.put("term_id",select_term_id);
                }
                coursePresenter.getCourseSelected(params);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("requestCode",requestCode+"");
        Log.e("resultCode",resultCode+"");
        switch (requestCode){
            case CollegeListActivity.SelectCollegeCode:
                if (data!=null){
                    select_school_id = data.getStringExtra("school_id");
                    select_school_name= data.getStringExtra("school_name");
                    select_college_id = data.getStringExtra("college_id");
                    select_college_name= data.getStringExtra("college_name");
                    college_name.setText(select_school_name+select_college_name);

                    Map<String,String> params = RS.getBaseParams(this);
                    params.put("school_id",select_school_id);
                    params.put("college_id",select_college_id);
                    if (StringUtil.isNotEmpty(select_term_id)){
                        params.put("term_id",select_term_id);
                    }
                    coursePresenter.getCourseSelected(params);
                }
                break;
            case TermListActivity.SelectTermCode:
                if (data!=null){
                    select_term_id = data.getStringExtra("term_id");
                    select_term_name= data.getStringExtra("term_name");
                    term_name.setText(select_term_name);

                    Map<String,String> params = RS.getBaseParams(this);
                    params.put("term_id",select_term_id);
                    if (StringUtil.isNotEmpty(select_school_id)&&StringUtil.isNotEmpty(select_college_id)){
                        params.put("school_id",select_school_id);
                        params.put("college_id",select_college_id);
                    }
                    coursePresenter.getCourseSelected(params);
                }
                break;
        }
    }
}
