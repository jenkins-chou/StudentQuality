package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.MatchDetailActivity;
import com.jenking.spandroid.activity.common.MatchOperateActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerMatchActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @OnClick(R.id.add_match)
    void add_match(){
        Intent intent = new Intent(this, MatchOperateActivity.class);
        startActivity(intent);

    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_match);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        baseRecyclerAdapter = new BaseRecyclerAdapter(this,datas,R.layout.activity_manager_match_item) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerMatchActivity.this, MatchDetailActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);
    }
}
