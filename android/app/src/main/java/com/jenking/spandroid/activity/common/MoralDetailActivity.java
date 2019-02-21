package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.MoralModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.MoralPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MoralDetailActivity extends BaseActivity {

    private MoralModel moralModel;
    private MoralPresenter moralPresenter;

    @BindView(R.id.moral_name)
    TextView moral_name;
    @BindView(R.id.moral_abstract)
    TextView moral_abstract;
    @BindView(R.id.moral_detail)
    TextView moral_detail;
    @BindView(R.id.moral_type)
    TextView moral_type;
    @BindView(R.id.moral_score)
    TextView moral_score;
    @BindView(R.id.remark)
    TextView remark;
    
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify_moral)
    void modify_cert(){
        if (moralModel!=null){
            Intent intent = new Intent(this, MoralOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(moralModel));
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.delete_moral)
    void delete_cert(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (moralPresenter!=null&&moralModel!=null){
                            Map<String,String> params = RS.getBaseParams(MoralDetailActivity.this);
                            params.put("id",moralModel.getId());
                            moralPresenter.deleteMoral(params);
                        }
                    }
                }).show();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moral_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改

            String json = intent.getStringExtra("model");
            moralModel = new Gson().fromJson(json, MoralModel.class);

            if (moralModel!=null){

                moral_name.setText(moralModel.getMoral_name());
                moral_abstract.setText(moralModel.getMoral_abstract());
                moral_detail.setText(moralModel.getMoral_detail());
                moral_type.setText(moralModel.getMoral_type());
                moral_score.setText(moralModel.getMoral_score());
                remark.setText(moralModel.getRemark());
            }
        }

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
                if (isSuccess){
                    Toast.makeText(MoralDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void getAllMorals(boolean isSuccess, Object object) {

            }
        });
    }
}
