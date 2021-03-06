package com.jenking.spandroid.activity.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
import com.jenking.spandroid.models.impl.UserCourseDetail;
import com.jenking.spandroid.presenter.CoursePresenter;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TeachScoreOperateActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @OnClick(R.id.back)
    void back(){
        finish();
    }



    private Map<String,String> scoreMap;
    private List<UserCourseDetail> userCourseDetails;
    private UserCoursePresenter userCoursePresenter;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    @OnClick(R.id.submit)    void submit(){
        String sql = "UPDATE user_course SET user_course_score = CASE id";
        String sqlFooter = " END WHERE id IN (";
        Iterator it = scoreMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println("Key: " + e.getKey() + "--Value: "
                    + e.getValue());
            sql += " WHEN "+ e.getKey() + " THEN " + e.getValue();
            sqlFooter += e.getKey()+",";
        }
        sqlFooter = sqlFooter.substring(0,sqlFooter.length()-1);
        sql += sqlFooter;
        sql += ")";
        Log.e("sql",sql);

        Map<String,String> params = RS.getBaseParams(this);
        params.put("sql",sql);
        userCoursePresenter.excute(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_operate);
    }

    @Override
    public void initData() {
        super.initData();
        userCourseDetails = new ArrayList<>();
        scoreMap = new HashMap<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<UserCourseDetail>(this,userCourseDetails,R.layout.activity_score_operate_item) {
            @Override
            protected void convert(BaseViewHolder helper, final UserCourseDetail item) {
                helper.setText(R.id.realname,item.getRealname());
                helper.setText(R.id.class_name,item.getClass_name());
                helper.setText(R.id.edittext,item.getUser_course_score());
                scoreMap.put(item.getId(),item.getUser_course_score());
                EditText editText = helper.getView(R.id.edittext);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        scoreMap.put(item.getId(),charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
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

            }

            @Override
            public void deleteByUserIdAndCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void addCourseTypeUser(boolean isSuccess, Object object) {

            }

            @Override
            public void getUserByCourseId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userCourseDetails.clear();
                        userCourseDetails = resultModel.getData()!=null?resultModel.getData():userCourseDetails;
                        baseRecyclerAdapter.setData(userCourseDetails);
                    }
                }
            }

            @Override
            public void excute(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(TeachScoreOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        if (getIntent()!=null&& StringUtil.isNotEmpty(getIntent().getStringExtra("course_id"))){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("course_id",getIntent().getStringExtra("course_id"));
            userCoursePresenter.getUserByCourseId(params);
        }
    }
}
