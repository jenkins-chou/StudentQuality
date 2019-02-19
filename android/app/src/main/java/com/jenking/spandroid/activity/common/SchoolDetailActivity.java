package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerSchoolOperateActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.app.MyApp;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.SchoolModel;
import com.jenking.spandroid.presenter.SchoolPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SchoolDetailActivity extends BaseActivity {

    private SchoolModel schoolModel;
    @BindView(R.id.school_name)
    TextView school_name;
    @BindView(R.id.school_abstract)
    TextView school_abstract;
    @BindView(R.id.school_detail)
    TextView school_detail;
    @BindView(R.id.school_address)
    TextView school_address;
    @BindView(R.id.school_build_time)
    TextView school_build_time;

    private SchoolPresenter schoolPresenter;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

//    @OnClick(R.id.submit)
//    void submit(){
//
//    }

    @OnClick(R.id.modify_school)
    void modify_school(){
        if (schoolModel!=null){
            Intent intent = new Intent(this, ManagerSchoolOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(schoolModel));
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.delete_school)
    void delete_school(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (schoolPresenter!=null&&schoolModel!=null){
                            Map<String,String> params = RS.getBaseParams(SchoolDetailActivity.this);
                            params.put("id",schoolModel.getId());
                            schoolPresenter.deleteSchool(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null){
            String schoolJson = intent.getStringExtra("model");
            schoolModel = new Gson().fromJson(schoolJson,SchoolModel.class);
            if (schoolModel!=null){
                school_name.setText(schoolModel.getSchool_name());
                school_abstract.setText(schoolModel.getSchool_abstract());
                school_detail.setText(schoolModel.getSchool_detail());
                school_address.setText(schoolModel.getSchool_address());
                school_build_time.setText(schoolModel.getSchool_build_time());
            }
        }

        schoolPresenter = new SchoolPresenter(this);
        schoolPresenter.setOnCallBack(new SchoolPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }

            @Override
            public void addSchool(boolean isSuccess, Object object) {

            }

            @Override
            public void updateSchool(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteSchool(boolean isSuccess, Object object) {
                if (isSuccess) {
                    Toast.makeText(SchoolDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void getAllSchool(boolean isSuccess, Object object) {

            }
        });
    }
}
