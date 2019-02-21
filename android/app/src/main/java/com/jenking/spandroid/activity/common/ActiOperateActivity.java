package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ActiModel;
import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.ActiPresenter;
import com.jenking.spandroid.presenter.MatchPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ActiOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private ActiModel actiModel;
    private ActiPresenter actiPresenter;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.activity_name)
    EditText activity_name;
    @BindView(R.id.activity_time)
    EditText activity_time;
    @BindView(R.id.activity_abstract)
    EditText activity_abstract;
    @BindView(R.id.activity_detail)
    EditText activity_detail;
    @BindView(R.id.activity_stunum)
    EditText activity_stunum;
    @BindView(R.id.activity_score)
    EditText activity_score;
    @BindView(R.id.remark)
    EditText remark;
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        String activity_name_str = activity_name.getText().toString();
        String activity_score_str = activity_score.getText().toString();
        if (!StringUtil.isNotEmpty(activity_name_str)
                ||!StringUtil.isNotEmpty(activity_score_str)){
            Toast.makeText(this, "活动名称和活动加分必填", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (actiPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("activity_name",activity_name.getText().toString());
                params.put("activity_time",activity_time.getText().toString());
                params.put("activity_abstract",activity_abstract.getText().toString());
                params.put("activity_detail",activity_detail.getText().toString());
                params.put("activity_stunum",activity_stunum.getText().toString());
                params.put("activity_score",activity_score.getText().toString());
                params.put("remark",remark.getText().toString());
                if (isAddData){
                    actiPresenter.addActi(params);
                }else{
                    if (actiModel!=null){
                        params.put("id",actiModel.getId());
                        actiPresenter.updateActi(params);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

            String json = intent.getStringExtra("model");
            actiModel = new Gson().fromJson(json, ActiModel.class);

            if (actiModel!=null){
                activity_name.setText(actiModel.getActivity_name());
                activity_time.setText(actiModel.getActivity_time());
                activity_abstract.setText(actiModel.getActivity_abstract());
                activity_detail.setText(actiModel.getActivity_detail());
                activity_stunum.setText(actiModel.getActivity_stunum());
                activity_score.setText(actiModel.getActivity_score());
                remark.setText(actiModel.getRemark());
            }
        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        actiPresenter = new ActiPresenter(this);
        actiPresenter.setOnCallBack(new ActiPresenter.OnCallBack() {
            @Override
            public void addActi(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(ActiOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(ActiOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void deleteActi(boolean isSuccess, Object object) {

            }

            @Override
            public void updateActi(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(ActiOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void getAllActi(boolean isSuccess, Object object) {

            }
        });

    }
}
