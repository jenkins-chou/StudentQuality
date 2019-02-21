package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ActiModel;
import com.jenking.spandroid.presenter.ActiPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ActiDetailActivity extends BaseActivity {

    private ActiModel actiModel;
    private ActiPresenter actiPresenter;
    @BindView(R.id.activity_name)
    TextView activity_name;
    @BindView(R.id.activity_time)
    TextView activity_time;
    @BindView(R.id.activity_abstract)
    TextView activity_abstract;
    @BindView(R.id.activity_detail)
    TextView activity_detail;
    @BindView(R.id.activity_stunum)
    TextView activity_stunum;
    @BindView(R.id.activity_score)
    TextView activity_score;
    @BindView(R.id.remark)
    TextView remark;
    
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify_acti)
    void modify_match(){
        if (actiModel!=null){
            Intent intent = new Intent(this, ActiOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(actiModel));
            startActivity(intent);
        }
    }

    @OnClick(R.id.delete_acti)
    void delete_match(){

        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (actiPresenter!=null&&actiModel!=null){
                            Map<String,String> params = RS.getBaseParams(ActiDetailActivity.this);
                            params.put("id",actiModel.getId());
                            actiPresenter.deleteActi(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改

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
        }

        actiPresenter = new ActiPresenter(this);
        actiPresenter.setOnCallBack(new ActiPresenter.OnCallBack() {
            @Override
            public void addActi(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteActi(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(ActiDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void updateActi(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllActi(boolean isSuccess, Object object) {

            }
        });
    }
}
