package com.jenking.spandroid.activity.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

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
import com.jenking.spandroid.models.impl.UserCourseDetail;
import com.jenking.spandroid.presenter.CoursePresenter;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeachScoreOperateActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

//    private List<>
    private UserCoursePresenter userCoursePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_operate);
    }

    @Override
    public void initData() {
        super.initData();
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

            }

            @Override
            public void getUserByCourseId(boolean isSuccess, Object object) {

            }
        });

        if (getIntent()!=null&& StringUtil.isNotEmpty(getIntent().getStringExtra("course_id"))){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("course_id",getIntent().getStringExtra("course_id"));
            userCoursePresenter.getUserByCourseId(params);
        }
    }
}
