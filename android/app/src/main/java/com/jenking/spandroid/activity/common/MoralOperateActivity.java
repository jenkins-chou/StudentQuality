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
import com.jenking.spandroid.dialog.CommonBottomListDialog;
import com.jenking.spandroid.models.base.ActiModel;
import com.jenking.spandroid.models.base.MoralModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.MoralPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MoralOperateActivity extends BaseActivity {

    boolean isAddData = true;
    private MoralModel moralModel;
    private List<String> moralTypeList;
    private MoralPresenter moralPresenter;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.moral_name)
    EditText moral_name;
    @BindView(R.id.moral_abstract)
    EditText moral_abstract;
    @BindView(R.id.moral_detail)
    EditText moral_detail;
    @BindView(R.id.moral_type)
    TextView moral_type;
    @BindView(R.id.moral_score)
    EditText moral_score;
    @BindView(R.id.remark)
    EditText remark;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.moral_type)
    void moral_type(){
        CommonBottomListDialog commonBottomListDialog = new CommonBottomListDialog(this,"考核类型",moralTypeList,"",false) {
            @Override
            protected void setOnItemClickListener(String value) {
                moral_type.setText(value);
            }
        };
        commonBottomListDialog.show();
    }


    @OnClick(R.id.submit)
    void submit(){
        String moral_name_str = moral_name.getText().toString();
        String moral_type_str = moral_type.getText().toString();
        String moral_score_str = moral_score.getText().toString();
        if (!StringUtil.isNotEmpty(moral_name_str)
                ||!StringUtil.isNotEmpty(moral_type_str)
                ||!StringUtil.isNotEmpty(moral_score_str)){
            Toast.makeText(this, "名称、类型和加分必填", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (moralPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("moral_name",moral_name.getText().toString());
                params.put("moral_abstract",moral_abstract.getText().toString());
                params.put("moral_detail",moral_detail.getText().toString());
                params.put("moral_type",moral_type.getText().toString());
                params.put("moral_score",moral_score.getText().toString());
                params.put("remark",remark.getText().toString());
                if (isAddData){
                    moralPresenter.addMoral(params);
                }else{
                    if (moralModel!=null){
                        params.put("id",moralModel.getId());
                        moralPresenter.updateMoral(params);
                    }
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moral_operate);
    }

    @Override
    public void initData() {
        super.initData();
        moralTypeList = new ArrayList<>();
        moralTypeList.add("加分");
        moralTypeList.add("减分");

        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

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
        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        moralPresenter = new MoralPresenter(this);
        moralPresenter.setOnCallBack(new MoralPresenter.OnCallBack() {
            @Override
            public void addMoral(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(MoralOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(MoralOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateMoral(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(MoralOperateActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void deleteMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllMorals(boolean isSuccess, Object object) {

            }
        });

    }
}
