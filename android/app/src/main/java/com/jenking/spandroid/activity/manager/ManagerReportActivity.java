package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.ClassListActivity;
import com.jenking.spandroid.activity.common.CollegeListActivity;
import com.jenking.spandroid.activity.common.SchoolListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerReportActivity extends BaseActivity {

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

    private List<UserModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserPresenter userPresenter;

    @OnClick(R.id.select_class)
    void select_class(){
        Intent intent = new Intent(this,ClassListActivity.class);
        startActivityForResult(intent,ClassListActivity.SelectClassCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<UserModel>(this,datas,R.layout.activity_manager_report_item) {

            @Override
            protected void convert(BaseViewHolder helper, UserModel item) {
                TextView usertype_boy = helper.getView(R.id.usertype_boy);
                TextView usertype_girl = helper.getView(R.id.usertype_girl);
                usertype_boy.setVisibility(View.GONE);
                usertype_girl.setVisibility(View.GONE);
                helper.setText(R.id.username,item.getRealname());
                helper.setText(R.id.class_name,item.getSchool_name()+"--"+item.getCollege_name()+"--"+item.getClass_name());
                if (StringUtil.isEquals(item.getSex(),"男")){
                    usertype_boy.setVisibility(View.VISIBLE);
                }else if (StringUtil.isEquals(item.getSex(),"女")){

                }
            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {
            @Override
            public void login(boolean isSuccess, Object object) {

            }

            @Override
            public void addUser(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachers(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachersByCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllStudent(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void getStudentByClass(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userPresenter!=null){
            userPresenter.getAllStudent(RS.getBaseParams(this));
        }
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

                    if (userPresenter!=null){
                        Map<String,String> params = RS.getBaseParams(this);
                        params.put("class_id",select_class_id);
                        params.put("college_id",select_college_id);
                        params.put("school_id",select_school_id);
                        userPresenter.getStudentByClass(params);
                    }
                }
                break;
        }
    }


}
