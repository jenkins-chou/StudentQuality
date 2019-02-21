package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerTermActivity;
import com.jenking.spandroid.activity.manager.TermOperateActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.TermModel;
import com.jenking.spandroid.presenter.TermPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class TermDetailActivity extends BaseActivity {

    private TermModel termModel;
    private TermPresenter termPresenter;
    @BindView(R.id.term_name)
    TextView term_name;
    @BindView(R.id.remark)
    TextView remark;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify_term)
    void modify_term(){
        if (termModel!=null){
            Intent intent = new Intent(this,TermOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(termModel));
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.delete_term)
    void delete_term(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (termModel!=null&&termPresenter!=null){
                            Map<String,String> params = RS.getBaseParams(TermDetailActivity.this);
                            params.put("id",termModel.getId());
                            termPresenter.deleteTerm(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            String json = intent.getStringExtra("model");
            termModel = new Gson().fromJson(json, TermModel.class);
            if (termModel!=null){
                term_name.setText(termModel.getTerm_name());
                remark.setText(termModel.getRemark());
            }
        }

        termPresenter = new TermPresenter(this);
        termPresenter.setOnCallBack(new TermPresenter.OnCallBack() {
            @Override
            public void addTerm(boolean isSuccess, Object object) {
            }

            @Override
            public void updateTerm(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteTerm(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(TermDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void getAllTerms(boolean isSuccess, Object object) {

            }
        });
    }
}
