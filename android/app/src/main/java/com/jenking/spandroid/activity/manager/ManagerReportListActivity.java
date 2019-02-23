package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.jenking.spandroid.activity.common.MatchListActivity;
import com.jenking.spandroid.activity.common.UserCertOperateActivity;
import com.jenking.spandroid.activity.common.UserMatchDetailActivity;
import com.jenking.spandroid.activity.common.UserMatchOperateActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.presenter.UserMatchPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerReportListActivity extends BaseActivity {

    private String user_id;

    private List<UserMatchDetail> userMatchDetails;

    private BaseRecyclerAdapter matchAdapter;

    private UserMatchPresenter userMatchPresenter;

    @BindView(R.id.match_rv)
    RecyclerView match_rv;

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @OnClick(R.id.add_user_match)
    void add_user_match(){
        if (StringUtil.isNotEmpty(user_id)){
            Intent intent = new Intent(this,UserMatchOperateActivity.class);
            intent.putExtra("user_id",user_id);
            startActivity(intent);
        }
    }
    @OnClick(R.id.add_user_cert)
    void add_user_cert(){
        if (StringUtil.isNotEmpty(user_id)){
            Intent intent = new Intent(this,UserCertOperateActivity.class);
            intent.putExtra("user_id",user_id);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report_list);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("user_id"))){
            user_id= intent.getStringExtra("user_id");
            initUserMatch();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Map<String,String> params = RS.getBaseParams(this);
        params.put("user_id",user_id);
        if (userMatchPresenter!=null){
            userMatchPresenter.getUserMatchByUserId(params);
        }
    }

    private void initUserMatch(){
        userMatchDetails = new ArrayList<>();
        matchAdapter = new BaseRecyclerAdapter<UserMatchDetail>(this,userMatchDetails,R.layout.activity_manager_report_match_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserMatchDetail item) {
                helper.setText(R.id.match_name,item.getMatch_name());
                helper.setText(R.id.user_match_score,"加分:"+item.getUser_match_score()+"分");
            }
        };
        matchAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerReportListActivity.this,UserMatchDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userMatchDetails.get(position)));
                startActivityForResult(intent,MatchListActivity.SelectMatchCode);
            }
        });
        matchAdapter.openLoadAnimation(false);
        match_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        match_rv.setAdapter(matchAdapter);

        userMatchPresenter = new UserMatchPresenter(this);
        userMatchPresenter.setOnCallBack(new UserMatchPresenter.OnCallBack() {
            @Override
            public void getUserMatchByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userMatchDetails = resultModel.getData()!=null?resultModel.getData():userMatchDetails;
                        matchAdapter.setData(userMatchDetails);
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
    }
}
