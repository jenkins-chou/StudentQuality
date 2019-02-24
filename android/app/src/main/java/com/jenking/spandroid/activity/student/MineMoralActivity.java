package com.jenking.spandroid.activity.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.UserMoralDetailActivity;
import com.jenking.spandroid.activity.manager.ManagerReportListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserMoralDetail;
import com.jenking.spandroid.presenter.UserMoralPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineMoralActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<UserMoralDetail> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserMoralPresenter userMoralPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_moral);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<UserMoralDetail>(this,datas,R.layout.activity_mine_moral_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserMoralDetail item) {
                helper.setText(R.id.moral_name,item.getMoral_name());

                TextView add_type = helper.getView(R.id.add_type);
                TextView reduce_type = helper.getView(R.id.reduce_type);
                add_type.setVisibility(View.GONE);
                reduce_type.setVisibility(View.GONE);
                if (StringUtil.isEquals(item.getMoral_type(),"加分")){
                    add_type.setText("加分:"+item.getMoral_score()+"分");
                    add_type.setVisibility(View.VISIBLE);
                }else if (StringUtil.isEquals(item.getMoral_type(),"减分")){
                    reduce_type.setText("减分:"+item.getMoral_score()+"分");
                    reduce_type.setVisibility(View.VISIBLE);
                }
            }
        };

        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineMoralActivity.this,UserMoralDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        userMoralPresenter = new UserMoralPresenter(this);
        userMoralPresenter.setOnCallBack(new UserMoralPresenter.OnCallBack() {
            @Override
            public void getUserMoralByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void addUserMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserMoral(boolean isSuccess, Object object) {

            }
        });

        userMoralPresenter = new UserMoralPresenter(this);
        userMoralPresenter.setOnCallBack(new UserMoralPresenter.OnCallBack() {
            @Override
            public void getUserMoralByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void addUserMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserMoral(boolean isSuccess, Object object) {

            }
        });

        getData();
    }

    void getData(){
        if (AccountTool.isLogin(this)&&AccountTool.getLoginUser(this)!=null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            if (userMoralPresenter!=null){
                userMoralPresenter.getUserMoralByUserId(params);
            }
        }
    }
}
