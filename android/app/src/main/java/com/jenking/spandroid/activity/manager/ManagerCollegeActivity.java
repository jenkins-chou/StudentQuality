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

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.CollegeDetailActivity;
import com.jenking.spandroid.activity.common.CollegeOperateActivity;
import com.jenking.spandroid.activity.common.SchoolDetailActivity;
import com.jenking.spandroid.activity.common.SchoolListActivity;
import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CollegePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerCollegeActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.school_name)
    TextView school_name;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String select_school_id;
    private String select_school_name;
    private List<CollegeModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    private CollegePresenter collegePresenter;

    @OnClick(R.id.select_school)
    void select_school(){
        Intent intent = new Intent(this,SchoolListActivity.class);
        startActivityForResult(intent,SchoolListActivity.SelectSchoolCode);
    }

    @OnClick(R.id.add_college)
    void add_college(){
        Intent intent = new Intent(this,CollegeOperateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_college);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<CollegeModel>(this,datas,R.layout.activity_manager_college_item) {
            @Override
            protected void convert(BaseViewHolder helper, CollegeModel item) {
                helper.setText(R.id.college_name,item.getCollege_name());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerCollegeActivity.this,CollegeDetailActivity.class);
                startActivity(intent);
            }
        });

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        collegePresenter = new CollegePresenter(this);
        collegePresenter.setOnCallBack(new CollegePresenter.OnCallBack() {
            @Override
            public void addCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllCollege(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void updateCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteCollege(boolean isSuccess, Object object) {

            }
        });
//        collegePresenter.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("requestCode",requestCode+"");
        Log.e("resultCode",resultCode+"");
        switch (requestCode){
            case SchoolListActivity.SelectSchoolCode:
                if (data!=null){
                    select_school_id = data.getStringExtra("school_id");
                    select_school_name= data.getStringExtra("school_name");
                    school_name.setText(select_school_name);
                }
                break;
        }
    }
}
