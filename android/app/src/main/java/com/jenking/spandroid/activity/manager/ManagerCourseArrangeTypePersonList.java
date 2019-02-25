package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CourseModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserCourseDetail;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerCourseArrangeTypePersonList extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<UserCourseDetail> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserCoursePresenter userCoursePresenter;

    private String select_user_id;
    private String select_user_realname;
    private String select_class_id;

    @OnClick(R.id.add_course)
    void add_course(){
        if (StringUtil.isNotEmpty(select_user_id)
                &&StringUtil.isNotEmpty(select_user_realname)
                &&StringUtil.isNotEmpty(select_class_id)){

            Intent intent = new Intent(this,ManagerCourseArrangeTypePersonAdd.class);
            intent.putExtra("user_id",select_user_id);
            intent.putExtra("class_id",select_class_id);
            intent.putExtra("user_realname",select_user_realname);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请返回重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_course_arrange_type_person_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        Intent intent = getIntent();
        if (intent!=null
                && StringUtil.isNotEmpty(intent.getStringExtra("user_id"))
                && StringUtil.isNotEmpty(intent.getStringExtra("class_id"))){
            select_user_id = intent.getStringExtra("user_id");
            select_user_realname = intent.getStringExtra("user_realname");
            select_class_id = intent.getStringExtra("class_id");

        }

        baseRecyclerAdapter = new BaseRecyclerAdapter<UserCourseDetail>(this,datas,R.layout.activity_course_arrange_type_class_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserCourseDetail item) {
                helper.setText(R.id.course_name,item.getCourse_name());
                helper.setText(R.id.college_name,item.getSchool_name()+"--"+item.getCollege_name());
                helper.setText(R.id.college_type,item.getCourse_type());
                helper.setText(R.id.college_term,item.getTerm_name());

                TextView remove_course = helper.getView(R.id.remove_course);
                remove_course.setTag(item.getId());
                remove_course.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,String> params = RS.getBaseParams(ManagerCourseArrangeTypePersonList.this);
                        params.put("course_id",view.getTag().toString());
                        params.put("user_id",select_user_id);
                        Log.e("deleteByUser",params.toString());
                        confirmToRemove(params);

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
                if (isSuccess){
                    Toast.makeText(ManagerCourseArrangeTypePersonList.this, "删除成功，正在刷新数据", Toast.LENGTH_LONG).show();
                    Map<String,String> params = RS.getBaseParams(ManagerCourseArrangeTypePersonList.this);
                    params.put("user_id",select_user_id);
                    userCoursePresenter.getCoursesByUserId(params);
                }
            }

            @Override
            public void addCourseTypeUser(boolean isSuccess, Object object) {

            }

            @Override
            public void getUserByCourseId(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userCoursePresenter!=null&&StringUtil.isNotEmpty(select_class_id)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",select_user_id);
            userCoursePresenter.getCoursesByUserId(params);
        }
    }

    void confirmToRemove(final Map<String,String> params){
        if (params==null)return;
        CommonTipsDialog.create(this,"温馨提示","确认要移除吗?",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {
                    }
                    @Override
                    public void confirm() {
                        if (userCoursePresenter!=null){
                            userCoursePresenter.deleteByUserIdAndCourseId(params);
                        }
                    }
                }).show();
    }
}
