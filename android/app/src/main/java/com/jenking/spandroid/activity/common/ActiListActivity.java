package com.jenking.spandroid.activity.common;

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
import com.jenking.spandroid.activity.manager.ManagerActiActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ActiModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.ActiPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActiListActivity extends BaseActivity {

    public static final int SelectActiCode = 3002;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<ActiModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private ActiPresenter actiPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<ActiModel>(this,datas,R.layout.activity_acti_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, ActiModel item) {
                helper.setText(R.id.acti_name,item.getActivity_name());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("acti_id",datas.get(position).getId());
                intent.putExtra("acti_name",datas.get(position).getActivity_name());
                setResult(SelectActiCode,intent);
                finish();
            }
        });

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        actiPresenter = new ActiPresenter(this);
        actiPresenter.setOnCallBack(new ActiPresenter.OnCallBack() {

            @Override
            public void addActi(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteActi(boolean isSuccess, Object object) {

            }

            @Override
            public void updateActi(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllActi(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }
        });

        actiPresenter.getAllActi(RS.getBaseParams(this));
    }

}
