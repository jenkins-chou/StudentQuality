package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.CertDetailActivity;
import com.jenking.spandroid.activity.common.MoralDetailActivity;
import com.jenking.spandroid.activity.common.MoralOperateActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.MoralModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.MoralPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerMoralActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<MoralModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private MoralPresenter moralPresenter;

    @OnClick(R.id.add_moral)
    void add_moral(){
        Intent intent = new Intent(this, MoralOperateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_moral);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<MoralModel>(this,datas,R.layout.activity_manager_moral_item) {
            @Override
            protected void convert(BaseViewHolder helper, MoralModel item) {
                helper.setText(R.id.moral_name,item.getMoral_name());

                TextView add_type = helper.getView(R.id.add_type);
                TextView reduce_type = helper.getView(R.id.reduce_type);
                add_type.setVisibility(View.GONE);
                reduce_type.setVisibility(View.GONE);
                if (StringUtil.isEquals(item.getMoral_type(),"加分")){
                    add_type.setText("加分"+item.getMoral_score());
                    add_type.setVisibility(View.VISIBLE);
                }else if (StringUtil.isEquals(item.getMoral_type(),"减分")){
                    reduce_type.setText("减分"+item.getMoral_score());
                    reduce_type.setVisibility(View.VISIBLE);
                }
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerMoralActivity.this, MoralDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);


        moralPresenter = new MoralPresenter(this);
        moralPresenter.setOnCallBack(new MoralPresenter.OnCallBack() {
            @Override
            public void addMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void updateMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllMorals(boolean isSuccess, Object object) {
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
        if (moralPresenter!=null){
            moralPresenter.getAllMorals(RS.getBaseParams(this));
        }
    }
}
