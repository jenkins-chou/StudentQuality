package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.TeacherOperateActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.TermModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.TermPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TermOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private TermModel termModel;
    private TermPresenter termPresenter;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.term_name)
    EditText term_name;
    @BindView(R.id.remark)
    EditText remark;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        String term_name_str  = term_name.getText().toString();
        String remark_str  = remark.getText().toString();

        if (!StringUtil.isNotEmpty(term_name_str)){
            Toast.makeText(this, "学期名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (termPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("term_name",term_name_str);
                params.put("remark",remark_str+"");
                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    termPresenter.addTerm(params);
                }else{
                    if (termModel!=null){
                        params.put("id",termModel.getId());
                        termPresenter.updateTerm(params);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_operate);
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
            termModel = new Gson().fromJson(json, TermModel.class);
            if (termModel!=null){

            }
        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        termPresenter = new TermPresenter(this);
        termPresenter.setOnCallBack(new TermPresenter.OnCallBack() {
            @Override
            public void addTerm(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(TermOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(TermOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void updateTerm(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(TermOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void deleteTerm(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllTerms(boolean isSuccess, Object object) {

            }
        });
    }
}
