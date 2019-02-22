package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.ClassListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerCourseArrangeTypeClass extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.class_name)
    TextView class_name;


    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;
    private String select_class_id;
    private String select_class_name;

    private List<CourseModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserCoursePresenter userCoursePresenter;

    @OnClick(R.id.arrange_course)
    void arrange_course(){
        if (StringUtil.isNotEmpty(select_class_id)
                &&StringUtil.isNotEmpty(select_college_id)
                &&StringUtil.isNotEmpty(select_school_id)){
            Intent intent = new Intent(this,ManagerCourseArrangeTypeClassAdd.class);
            intent.putExtra("class_id",select_class_id);
            intent.putExtra("college_id",select_college_id);
            intent.putExtra("school_id",select_school_id);
            intent.putExtra("class_name",select_class_name);
            intent.putExtra("college_name",select_college_name);
            intent.putExtra("school_name",select_school_name);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请选择班级", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.select_class)
    void select_class(){
        Intent intent = new Intent(this,ClassListActivity.class);
        startActivityForResult(intent,ClassListActivity.SelectClassCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_course_arrange_type_class);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<CourseModel>(this,datas,R.layout.activity_course_arrange_type_class_item) {
            @Override
            protected void convert(BaseViewHolder helper, CourseModel item) {
                helper.setText(R.id.course_name,item.getCourse_name());
                helper.setText(R.id.college_name,item.getSchool_name()+"--"+item.getCollege_name());
                helper.setText(R.id.college_type,item.getCourse_type());
                helper.setText(R.id.college_term,item.getTerm_name());
            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        userCoursePresenter = new UserCoursePresenter(this);
        userCoursePresenter.setOnCallBack(new UserCoursePresenter.OnCallBack() {
            @Override
            public void getCoursesByClassId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void deleteByClassIdAndCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void addCourseTypeClass(boolean isSuccess, Object object) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ClassListActivity.SelectClassCode:
                if (data != null) {
                    select_school_id = data.getStringExtra("school_id");
                    select_school_name = data.getStringExtra("school_name");
                    select_college_id = data.getStringExtra("college_id");
                    select_college_name = data.getStringExtra("college_name");
                    select_class_id = data.getStringExtra("class_id");
                    select_class_name = data.getStringExtra("class_name");
                    class_name.setText(select_school_name +"--"+ select_college_name+"--"+select_class_name);

                    Map<String,String> params = RS.getBaseParams(this);
                    params.put("class_id",select_class_id);
                    userCoursePresenter.getCoursesByClassId(params);
                }
                break;
        }
    }
}
