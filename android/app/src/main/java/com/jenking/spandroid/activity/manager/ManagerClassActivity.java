package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.CollegeListActivity;
import com.jenking.spandroid.activity.common.SchoolListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerClassActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    @OnClick(R.id.select_school)
    void select_school(){
        Intent intent = new Intent(this,SchoolListActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.select_college)
    void select_college(){
        Intent intent = new Intent(this,CollegeListActivity.class);
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
        datas.add("");
        datas.add("");
        datas.add("");
        baseRecyclerAdapter = new BaseRecyclerAdapter(this,datas,R.layout.activity_manager_class_item) {

            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);
    }
}
