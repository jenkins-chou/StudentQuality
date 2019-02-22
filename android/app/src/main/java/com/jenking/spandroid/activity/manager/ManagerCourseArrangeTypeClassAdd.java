package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserCourseModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

public class ManagerCourseArrangeTypeClassAdd extends BaseActivity {

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;
    private String select_class_id;
    private String select_class_name;

    private List<UserModel> userModelList;
    private UserPresenter userPresenter;
    private UserCoursePresenter userCoursePresenter;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        if (userPresenter!=null&&StringUtil.isNotEmpty(select_class_id)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("class_id",select_class_id);
            params.put("school_id",select_school_id);
            params.put("college_id",select_college_id);
            userPresenter.getStudentByClass(params);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_course_arrange_type_class_add);
    }

    @Override
    public void initData() {
        super.initData();
        userModelList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent!=null
                && StringUtil.isNotEmpty(intent.getStringExtra("class_id"))){
            select_school_id = intent.getStringExtra("school_id");
            select_school_name = intent.getStringExtra("school_name");
            select_college_id = intent.getStringExtra("college_id");
            select_college_name = intent.getStringExtra("college_name");
            select_class_id = intent.getStringExtra("class_id");
            select_class_name = intent.getStringExtra("class_name");
        }

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

            }

            @Override
            public void getStudentByClass(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userModelList = resultModel.getData()!=null?resultModel.getData():userModelList;

                        if (userModelList!=null&&userModelList.size()>0){
                            String sql = "insert into user_course(user_id,course_id,class_id,create_time,del) values ";
                            for (int i =0;i<userModelList.size();i++){
                                if (i==userModelList.size()-1){
                                    sql = sql + "('"+userModelList.get(i).getId()+"','"+"1"+"','"+select_class_id+"','"+StringUtil.getTime()+"','"+"noraml"+"');";
                                }else{
                                    sql = sql + "('"+userModelList.get(i).getId()+"','"+"1"+"','"+select_class_id+"','"+StringUtil.getTime()+"','"+"noraml"+"'),";
                                }
                            }
                            Map<String,String> params = RS.getBaseParams(ManagerCourseArrangeTypeClassAdd.this);
                            params.put("class_id",select_class_id);
                            params.put("course_id","1");
                            params.put("sql",sql);
                            Log.e("params",sql);
                            userCoursePresenter.addCourseTypeClass(params);

                        }else{
                            Toast.makeText(ManagerCourseArrangeTypeClassAdd.this, "该班级还没有学生，暂不能安排课程", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        userCoursePresenter = new UserCoursePresenter(this);
        userCoursePresenter.setOnCallBack(new UserCoursePresenter.OnCallBack() {
            @Override
            public void getCoursesByClassId(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteByClassIdAndCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void addCourseTypeClass(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(ManagerCourseArrangeTypeClassAdd.this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
