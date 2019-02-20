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
import com.jenking.spandroid.activity.common.CollegeListActivity;
import com.jenking.spandroid.activity.common.SchoolListActivity;
import com.jenking.spandroid.activity.common.TeacherDetailActivity;
import com.jenking.spandroid.activity.common.TeacherOperateActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerTeacherActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.college_name)
    TextView college_name;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;

    @OnClick(R.id.add_teacher)
    void add_teacher(){
        Intent intent = new Intent(this, TeacherOperateActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.select_college)
    void select_college(){
        Intent intent = new Intent(this,CollegeListActivity.class);
        startActivityForResult(intent,CollegeListActivity.SelectCollegeCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_teacher);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter(this,datas,R.layout.activity_manager_teacher_item) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerTeacherActivity.this, TeacherDetailActivity.class);
                startActivity(intent);
            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);
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
                }
                break;
        }
    }
}
