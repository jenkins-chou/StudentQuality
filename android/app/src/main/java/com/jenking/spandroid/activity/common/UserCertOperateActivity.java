package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.presenter.UserCertPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserCertOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private UserCertDetail userCertDetail;
    private UserCertPresenter userCertPresenter;

    private String select_user_id;
    private String select_cert_id;
    private String select_cert_name;
    
    @BindView(R.id.cert_name)
    TextView cert_name;
    @BindView(R.id.get_cert_time)
    EditText get_cert_time;
    @BindView(R.id.remark)
    EditText remark;

    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @OnClick({R.id.select_cert,R.id.cert_name})
    void select_match(){
        Intent intent = new Intent(this,CertListActivity.class);
        startActivityForResult(intent,CertListActivity.SelectCertCode);
    }

    @OnClick(R.id.submit)
    void submit(){
        if (!StringUtil.isNotEmpty(select_cert_id)
                ||!StringUtil.isNotEmpty(select_user_id)){
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (userCertPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("user_id",select_user_id);
                params.put("certificate_id",select_cert_id);
                params.put("get_certificate_time",get_cert_time.getText().toString());
                params.put("create_time",StringUtil.getTime());
                params.put("remark",remark.getText().toString());
                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    userCertPresenter.addUserCert(params);
                }else{
                    if (userCertDetail!=null){
                        params.put("id",userCertDetail.getId());
                        userCertPresenter.updateUserCert(params);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cert_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

            String json = intent.getStringExtra("model");
            userCertDetail = new Gson().fromJson(json, UserCertDetail.class);

            if (userCertDetail!=null){
                cert_name.setText(userCertDetail.getCertificate_name());
                get_cert_time.setText(userCertDetail.getGet_certificate_time());
                remark.setText(userCertDetail.getRemark());
            }
        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("user_id"))){
            select_user_id= intent.getStringExtra("user_id");
        }

        userCertPresenter = new UserCertPresenter(this);
        userCertPresenter.setOnCallBack(new UserCertPresenter.OnCallBack() {
            @Override
            public void getUserCertByUserId(boolean isSuccess, Object object) {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CertListActivity.SelectCertCode:
                if (data!=null){
                    select_cert_id = data.getStringExtra("cert_id");
                    select_cert_name = data.getStringExtra("cert_name");
                    cert_name.setText(select_cert_name);
                }
                break;
        }
    }
}
