package com.jenking.spandroid.activity.student;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.CourseListActivity;
import com.jenking.spandroid.activity.manager.ManagerCourseArrangeTypePersonAdd;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentSelectCourseActivity extends BaseActivity {

    private String select_course_id;
    private String select_course_name;
    private String select_course_type;

    private List<UserModel> userModelList;
    private UserCoursePresenter userCoursePresenter;

    @BindView(R.id.course_name)
    TextView course_name;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.select_course)
    void select_course(){
        Intent intent = new Intent(this,CourseListActivity.class);
        startActivityForResult(intent,CourseListActivity.SelectCourseCode);
    }

    @OnClick(R.id.submit)
    void submit(){
        if (AccountTool.isLogin(this)
                &&StringUtil.isNotEmpty(select_course_id)){

            String user_id = AccountTool.getLoginUser(this).getId();
            String class_id = AccountTool.getLoginUser(this).getClass_id();
            String sql = "insert into user_course(user_id,course_id,class_id,create_time,del) value ";
            sql += "('"+user_id+"','"+select_course_id+"','"+class_id+"','"+StringUtil.getTime()+"','"+"normal"+"')";
            Map<String,String> params = RS.getBaseParams(this);
            params.put("course_id",select_course_id);
            params.put("user_id",user_id);
            params.put("sql",sql);
            Log.e("params",""+params.toString());
            userCoursePresenter.addCourseTypeUser(params);
        }else{
            Toast.makeText(this, "请选择课程", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select_course);
    }


    @Override
    public void initData() {
        super.initData();
        userModelList = new ArrayList<>();
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

            }

            @Override
            public void getCoursesByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteByUserIdAndCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void addCourseTypeUser(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(StudentSelectCourseActivity.this, "选课成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(StudentSelectCourseActivity.this, "已存在该课程", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void getUserByCourseId(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case CourseListActivity.SelectCourseCode:

                    select_course_type= data.getStringExtra("course_type");
                    if (StringUtil.isEquals(select_course_type,"选修")){
                        select_course_id = data.getStringExtra("course_id");
                        select_course_name= data.getStringExtra("course_name");
                        course_name.setText(select_course_name);
                    }else{
                        CommonTipsDialog.showTip(this,"温馨提示","请注意，非选修不可选课，必修课由管理员负责安排",false);
                    }
                    break;
            }
        }
    }

}
