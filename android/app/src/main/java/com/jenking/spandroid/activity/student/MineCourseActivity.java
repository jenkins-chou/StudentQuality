package com.jenking.spandroid.activity.student;

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
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserCourseDetail;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineCourseActivity extends BaseActivity {
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    private List<UserCourseDetail> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserCoursePresenter userCoursePresenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_class);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<UserCourseDetail>(this,datas,R.layout.activity_mine_course_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserCourseDetail item) {
                helper.setText(R.id.course_name,item.getCourse_name());
                helper.setText(R.id.course_type,item.getCourse_type());
                if (StringUtil.isNotEmpty(item.getUser_course_score())){
                    helper.setText(R.id.user_course_score,item.getCourse_type()+"分");
                }else{
                    helper.setText(R.id.user_course_score,"未公布成绩");
                }
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineCourseActivity.this,CourseDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

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
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void deleteByUserIdAndCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void addCourseTypeUser(boolean isSuccess, Object object) {

            }
        });

        if (AccountTool.isLogin(this)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            userCoursePresenter.getCoursesByUserId(params);
        }
    }
}
