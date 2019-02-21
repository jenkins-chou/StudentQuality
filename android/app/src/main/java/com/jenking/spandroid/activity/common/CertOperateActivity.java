package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerCertActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.CertificateModel;
import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CertPresenter;
import com.jenking.spandroid.presenter.MatchPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CertOperateActivity extends BaseActivity {

    private boolean isAddData = true;
    private CertificateModel certificateModel;
    private CertPresenter certPresenter;

    @BindView(R.id.operate_tips)
    TextView operate_tips;
    @BindView(R.id.certificate_name)
    EditText certificate_name;
    @BindView(R.id.certificate_sponsor)
    EditText certificate_sponsor;
    @BindView(R.id.certificate_abstract)
    EditText certificate_abstract;
    @BindView(R.id.certificate_detail)
    EditText certificate_detail;
    @BindView(R.id.certificate_score)
    EditText certificate_score;
    @BindView(R.id.certificate_manager)
    EditText certificate_manager;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        String certificate_name_str = certificate_name.getText().toString();
        String certificate_score_str = certificate_score.getText().toString();

        if (!StringUtil.isNotEmpty(certificate_name_str)
                ||!StringUtil.isNotEmpty(certificate_score_str)){
            Toast.makeText(this, "证书名称和加分必填", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (certPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("certificate_name",certificate_name.getText().toString());
                params.put("certificate_sponsor",certificate_sponsor.getText().toString());
                params.put("certificate_abstract",certificate_abstract.getText().toString());
                params.put("certificate_detail",certificate_detail.getText().toString());
                params.put("certificate_score",certificate_score.getText().toString());
                params.put("certificate_manager",certificate_manager.getText().toString());
                if (isAddData){
                    certPresenter.addCert(params);
                }else{
                    if (certificateModel!=null){
                        params.put("id",certificateModel.getId());
                        certPresenter.updateCert(params);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cert_operate);
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
            certificateModel = new Gson().fromJson(json, CertificateModel.class);

            if (certificateModel!=null){
                certificate_name.setText(certificateModel.getCertificate_name());
                certificate_sponsor.setText(certificateModel.getCertificate_sponsor());
                certificate_abstract.setText(certificateModel.getCertificate_abstract());
                certificate_detail.setText(certificateModel.getCertificate_detail());
                certificate_score.setText(certificateModel.getCertificate_score());
                certificate_manager.setText(certificateModel.getCertificate_manager());
            }

        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        certPresenter = new CertPresenter(this);
        certPresenter.setOnCallBack(new CertPresenter.OnCallBack() {
            @Override
            public void addCert(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(CertOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(CertOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void deleteCert(boolean isSuccess, Object object) {

            }

            @Override
            public void updateCert(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(CertOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void getAllCerts(boolean isSuccess, Object object) {

            }
        });
    }
}
