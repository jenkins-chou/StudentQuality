package com.jenking.spandroid.activity.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.UserActiDetailActivity;
import com.jenking.spandroid.activity.common.UserCertDetailActivity;
import com.jenking.spandroid.activity.common.UserMatchDetailActivity;
import com.jenking.spandroid.activity.common.UserMoralDetailActivity;
import com.jenking.spandroid.activity.manager.ManagerReportListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserActivityDetail;
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.models.impl.UserMoralDetail;
import com.jenking.spandroid.presenter.UserActivityPresenter;
import com.jenking.spandroid.presenter.UserCertPresenter;
import com.jenking.spandroid.presenter.UserMatchPresenter;
import com.jenking.spandroid.presenter.UserMoralPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineReportActivity extends BaseActivity {

    private String user_id;

    private List<UserMatchDetail> userMatchDetails;
    private List<UserCertDetail> userCertDetails;
    private List<UserActivityDetail> userActivityDetails;
    private List<UserMoralDetail> userMoralDetails;

    private BaseRecyclerAdapter matchAdapter;
    private BaseRecyclerAdapter certAdapter;
    private BaseRecyclerAdapter actiAdapter;
    private BaseRecyclerAdapter moralAdapter;

    private UserMatchPresenter userMatchPresenter;
    private UserCertPresenter userCertPresenter;
    private UserActivityPresenter userActivityPresenter;
    private UserMoralPresenter userMoralPresenter;

    @BindView(R.id.match_rv)
    RecyclerView match_rv;
    @BindView(R.id.cert_rv)
    RecyclerView cert_rv;
    @BindView(R.id.acti_rv)
    RecyclerView acti_rv;
    @BindView(R.id.moral_rv)
    RecyclerView moral_rv;


    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_report);
    }

    @Override
    public void initData() {
        super.initData();
        if (AccountTool.isLogin(this)&&AccountTool.getLoginUser(this)!=null){
            user_id = AccountTool.getLoginUser(this).getId();
            initUserMatch();
            initUserCert();
            initUserActi();
            initUserMoral();

            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",user_id);
            if (userMatchPresenter!=null){
                userMatchPresenter.getUserMatchByUserId(params);
            }

            if (userCertPresenter!=null){
                userCertPresenter.getUserCertByUserId(params);
            }

            if (userActivityPresenter!=null){
                userActivityPresenter.getUserActivityByUserId(params);
            }

            if (userMoralPresenter!=null){
                userMoralPresenter.getUserMoralByUserId(params);
            }
        }else{
            Log.e("what?","????");
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
                Intent intent = new Intent(MineReportActivity.this,UserMatchDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userMatchDetails.get(position)));
                startActivity(intent);
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

    private void initUserCert(){
        userCertDetails = new ArrayList<>();
        certAdapter = new BaseRecyclerAdapter<UserCertDetail>(this,userCertDetails,R.layout.activity_manager_report_cert_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserCertDetail item) {
                helper.setText(R.id.cert_name,item.getCertificate_name());
                helper.setText(R.id.user_cert_score,"加分:"+item.getCertificate_score()+"分");
            }
        };
        certAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,UserCertDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userCertDetails.get(position)));
                startActivity(intent);
            }
        });
        certAdapter.openLoadAnimation(false);
        cert_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        cert_rv.setAdapter(certAdapter);

        userCertPresenter = new UserCertPresenter(this);
        userCertPresenter.setOnCallBack(new UserCertPresenter.OnCallBack() {
            @Override
            public void getUserCertByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userCertDetails = resultModel.getData()!=null?resultModel.getData():userMatchDetails;
                        certAdapter.setData(userCertDetails);
                    }
                }
            }

            @Override
            public void addUserCert(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserCert(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserCert(boolean isSuccess, Object object) {

            }
        });
    }

    private void initUserActi(){
        userActivityDetails = new ArrayList<>();
        actiAdapter = new BaseRecyclerAdapter<UserActivityDetail>(this,userActivityDetails,R.layout.activity_manager_report_acti_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserActivityDetail item) {
                helper.setText(R.id.acti_name,item.getActivity_name());
                helper.setText(R.id.user_acti_score,"加分:"+item.getUser_activity_score()+"分");
            }
        };
        actiAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,UserActiDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userActivityDetails.get(position)));
                startActivity(intent);
            }
        });
        actiAdapter.openLoadAnimation(false);
        acti_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        acti_rv.setAdapter(actiAdapter);

        userActivityPresenter = new UserActivityPresenter(this);
        userActivityPresenter.setOnCallBack(new UserActivityPresenter.OnCallBack() {
            @Override
            public void getUserActivityByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userActivityDetails = resultModel.getData()!=null?resultModel.getData():userActivityDetails;
                        actiAdapter.setData(userActivityDetails);
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
    }

    private void initUserMoral(){
        userMoralDetails = new ArrayList<>();
        moralAdapter = new BaseRecyclerAdapter<UserMoralDetail>(this,userMoralDetails,R.layout.activity_manager_report_moral_item) {
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
        moralAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,UserMoralDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userMoralDetails.get(position)));
                startActivity(intent);
            }
        });
        moralAdapter.openLoadAnimation(false);
        moral_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        moral_rv.setAdapter(moralAdapter);

        userMoralPresenter = new UserMoralPresenter(this);
        userMoralPresenter.setOnCallBack(new UserMoralPresenter.OnCallBack() {
            @Override
            public void getUserMoralByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userMoralDetails = resultModel.getData()!=null?resultModel.getData():userMoralDetails;
                        moralAdapter.setData(userMoralDetails);
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
    }

}
