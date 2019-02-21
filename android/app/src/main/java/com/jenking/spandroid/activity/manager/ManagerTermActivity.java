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
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.ClassDetailActivity;
import com.jenking.spandroid.activity.common.TermDetailActivity;
import com.jenking.spandroid.models.base.ClassModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerTermActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    private List<String> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.add_term)
    void add_term(){
        Intent intent = new Intent(ManagerTermActivity.this,TermOperateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_term);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        baseRecyclerAdapter = new BaseRecyclerAdapter<String>(this,datas,R.layout.activity_manager_term_item) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerTermActivity.this,TermDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);
    }
}
