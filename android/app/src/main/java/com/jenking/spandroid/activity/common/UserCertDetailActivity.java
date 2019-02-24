package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.presenter.UserCertPresenter;
import com.jenking.spandroid.presenter.UserMatchPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserCertDetailActivity extends BaseActivity {

    private String select_user_id;
    private UserCertDetail userCertDetail;
    private UserCertPresenter userCertPresenter;


    @BindView(R.id.cert_name)
    TextView cert_name;
    @BindView(R.id.get_cert_time)
    TextView get_cert_time;
    @BindView(R.id.remark)
    TextView remark;

    @BindView(R.id.manager_bar)
    LinearLayout manager_bar;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify)
    void modify(){
        if (userCertDetail!=null){
            Intent intent = new Intent(this, UserCertOperateActivity.class);
            intent.putExtra("user_id",userCertDetail.getUser_id());
            intent.putExtra("model",new Gson().toJson(userCertDetail));
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.delete)
    void delete(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (userCertPresenter!=null&&userCertDetail!=null){
                            Map<String,String> params = RS.getBaseParams(UserCertDetailActivity.this);
                            params.put("id",userCertDetail.getId());
                            userCertPresenter.deleteUserCert(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cert_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改

            String json = intent.getStringExtra("model");
            userCertDetail = new Gson().fromJson(json, UserCertDetail.class);

            if (userCertDetail!=null){
                cert_name.setText(userCertDetail.getCertificate_name());
                get_cert_time.setText(userCertDetail.getGet_certificate_time());
                remark.setText(userCertDetail.getRemark());
            }
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
                if (isSuccess){
                    Toast.makeText(UserCertDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        if (AccountTool.isLogin(this)&&StringUtil.isEquals(AccountTool.getUserType(this),AccountTool.usertype_manager)){
            manager_bar.setVisibility(View.VISIBLE);
        }
    }

}
