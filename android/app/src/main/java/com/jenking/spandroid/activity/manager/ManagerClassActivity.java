package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.ClassDetailActivity;
import com.jenking.spandroid.activity.common.ClassOperateActivity;
import com.jenking.spandroid.activity.common.CollegeDetailActivity;
import com.jenking.spandroid.activity.common.CollegeListActivity;
import com.jenking.spandroid.activity.common.SchoolListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.ClassPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerClassActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.college_name)
    TextView college_name;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;

    private List<ClassModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    private ClassPresenter classPresenter;

    @OnClick(R.id.select_college)
    void select_college(){
        Intent intent = new Intent(this,CollegeListActivity.class);
        startActivityForResult(intent,CollegeListActivity.SelectCollegeCode);
    }

    @OnClick(R.id.add_class)
    void add_class(){
        Intent intent = new Intent(this, ClassOperateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_class);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<ClassModel>(this,datas,R.layout.activity_manager_class_item) {

            @Override
            protected void convert(BaseViewHolder helper, ClassModel item) {
                helper.setText(R.id.college_name,item.getSchool_name()+item.getCollege_name());
                helper.setText(R.id.class_name,item.getClass_name());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerClassActivity.this,ClassDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        classPresenter = new ClassPresenter(this);
        classPresenter.setOnCallBack(new ClassPresenter.OnCallBack() {
            @Override
            public void addClass(boolean isSuccess, Object object) {
            }

            @Override
            public void updateClass(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteClass(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllClass(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void getClassByCollege(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas.clear();
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
        if (StringUtil.isNotEmpty(select_school_id)&&StringUtil.isNotEmpty(select_college_id)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("school_id",select_school_id);
            params.put("college_id",select_college_id);
            classPresenter.getClassByCollege(params);
        }else{
            Intent intent = getIntent();
            String school_id = intent.getStringExtra("school_id");
            String college_id = intent.getStringExtra("college_id");
            if (intent!=null&&StringUtil.isNotEmpty(school_id)&&StringUtil.isNotEmpty(college_id)) {
                Map<String,String> params = RS.getBaseParams(this);
                params.put("school_id",school_id);
                params.put("college_id",college_id);
                classPresenter.getClassByCollege(params);
            }else{
                classPresenter.getAllClass(RS.getBaseParams(this));
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
                    classPresenter.getClassByCollege(params);
                }
                break;
        }
    }
}
