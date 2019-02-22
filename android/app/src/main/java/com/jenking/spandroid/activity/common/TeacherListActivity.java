package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.ClassPresenter;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeacherListActivity extends BaseActivity {

    public static final int SelectTeacherCode = 60001;
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.college_name)
    TextView college_name;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;


    private List<UserModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    private UserPresenter userPresenter;

    @OnClick(R.id.select_college)
    void select_college(){
        Intent intent = new Intent(this,CollegeListActivity.class);
        startActivityForResult(intent,CollegeListActivity.SelectCollegeCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<UserModel>(this,datas,R.layout.activity_teacher_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserModel item) {
                helper.setText(R.id.realname,item.getRealname());
                helper.setText(R.id.college_name,item.getSchool_name()+"---"+item.getCollege_name());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("teacher_id",datas.get(position).getId());
                intent.putExtra("teacher_name",datas.get(position).getRealname());
                intent.putExtra("college_id",datas.get(position).getCollege_id());
                intent.putExtra("college_name",datas.get(position).getCollege_name());
                intent.putExtra("school_id",datas.get(position).getSchool_id());
                intent.putExtra("school_name",datas.get(position).getSchool_name());
                setResult(SelectTeacherCode,intent);
                finish();
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
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
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void getTeachersByCollege(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void getAllStudent(boolean isSuccess, Object object) {

            }

            @Override
            public void getStudentByClass(boolean isSuccess, Object object) {

            }
        });
        userPresenter.getTeachers(RS.getBaseParams(this));
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
                    userPresenter.getTeachersByCollege(params);
                }
                break;
        }
    }
}
