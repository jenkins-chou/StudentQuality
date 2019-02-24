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
import com.jenking.spandroid.activity.common.UserMatchDetailActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.presenter.UserMatchPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineMatchActivity extends BaseActivity {
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<UserMatchDetail> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserMatchPresenter userMatchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_match);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<UserMatchDetail>(this,datas,R.layout.activity_mine_match_item) {

            @Override
            protected void convert(BaseViewHolder helper, UserMatchDetail item) {
                helper.setText(R.id.match_name,item.getMatch_name());
                helper.setText(R.id.match_time,item.getMatch_time());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineMatchActivity.this,UserMatchDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        userMatchPresenter = new UserMatchPresenter(this);
        userMatchPresenter.setOnCallBack(new UserMatchPresenter.OnCallBack() {
            @Override
            public void getUserMatchByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void addUserMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserMatch(boolean isSuccess, Object object) {

            }
        });
        getData();
    }

    void getData(){
        if (AccountTool.isLogin(this)&&AccountTool.getLoginUser(this)!=null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            if (userMatchPresenter!=null){
                userMatchPresenter.getUserMatchByUserId(params);
            }
        }
    }


}
