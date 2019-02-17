package com.jenking.spandroid.activity.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.presenter.AAAPresenterTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineActivityActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_activity);
    }

    @Override
    public void initData() {
        super.initData();

        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        baseRecyclerAdapter = new BaseRecyclerAdapter(this,datas,R.layout.activity_mine_activity_item) {

            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        AAAPresenterTemplate aaaPresenterTemplate = new AAAPresenterTemplate(this);
        aaaPresenterTemplate.setOnCallBack(new AAAPresenterTemplate.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        aaaPresenterTemplate.request(new HashMap<String, String>());//示例
    }


}
