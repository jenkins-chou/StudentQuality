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
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ClassModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.TermModel;
import com.jenking.spandroid.presenter.TermPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerTermActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    private List<TermModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private TermPresenter termPresenter;

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
        baseRecyclerAdapter = new BaseRecyclerAdapter<TermModel>(this,datas,R.layout.activity_manager_term_item) {
            @Override
            protected void convert(BaseViewHolder helper, TermModel item) {
                helper.setText(R.id.term_name,item.getTerm_name());
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

        termPresenter = new TermPresenter(this);
        termPresenter.setOnCallBack(new TermPresenter.OnCallBack() {
            @Override
            public void addTerm(boolean isSuccess, Object object) {

            }

            @Override
            public void updateTerm(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteTerm(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllTerms(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
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
        if (termPresenter!=null){
            termPresenter.getAllTerms(RS.getBaseParams(this));
        }
    }
}
