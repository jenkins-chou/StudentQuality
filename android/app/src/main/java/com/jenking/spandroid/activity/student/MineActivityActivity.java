package com.jenking.spandroid.activity.student;

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
import com.jenking.spandroid.activity.common.UserActiDetailActivity;
import com.jenking.spandroid.activity.common.UserMatchDetailActivity;
import com.jenking.spandroid.activity.manager.ManagerReportListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserActivityDetail;
import com.jenking.spandroid.presenter.AAAPresenterTemplate;
import com.jenking.spandroid.presenter.UserActivityPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineActivityActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<UserActivityDetail> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserActivityPresenter userActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_activity);
    }

    @Override
    public void initData() {
        super.initData();

        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<UserActivityDetail>(this,datas,R.layout.activity_mine_activity_item) {

            @Override
            protected void convert(BaseViewHolder helper, UserActivityDetail item) {
                helper.setText(R.id.acti_name,item.getActivity_name());
                helper.setText(R.id.acti_time,item.getActivity_time()+"");
            }
        };

        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineActivityActivity.this,UserActiDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        userActivityPresenter = new UserActivityPresenter(this);
        userActivityPresenter.setOnCallBack(new UserActivityPresenter.OnCallBack() {
            @Override
            public void getUserActivityByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void addUserActivity(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserActivity(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserActivity(boolean isSuccess, Object object) {

            }
        });
        getData();
    }

    void getData(){
        if (AccountTool.isLogin(this)&&AccountTool.getLoginUser(this)!=null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            if (userActivityPresenter!=null){
                userActivityPresenter.getUserActivityByUserId(params);
            }
        }
    }


}
