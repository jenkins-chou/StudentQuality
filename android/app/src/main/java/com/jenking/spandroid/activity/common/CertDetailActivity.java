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
import com.jenking.spandroid.models.base.CertificateModel;
import com.jenking.spandroid.presenter.CertPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CertDetailActivity extends BaseActivity {

    private CertificateModel certificateModel;
    private CertPresenter certPresenter;

    @BindView(R.id.certificate_name)
    TextView certificate_name;
    @BindView(R.id.certificate_sponsor)
    TextView certificate_sponsor;
    @BindView(R.id.certificate_abstract)
    TextView certificate_abstract;
    @BindView(R.id.certificate_detail)
    TextView certificate_detail;
    @BindView(R.id.certificate_score)
    TextView certificate_score;
    @BindView(R.id.certificate_manager)
    TextView certificate_manager;
    
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify_cert)
    void modify_cert(){
        if (certificateModel!=null) {
            Intent intent = new Intent(this, CertOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(certificateModel));
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.delete_cert)
    void delete_cert(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (certificateModel!=null) {
                            Map<String, String> params = RS.getBaseParams(CertDetailActivity.this);
                            params.put("id",certificateModel.getId());
                            certPresenter.deleteCert(params);
                        }
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cert_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改

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
        }
        certPresenter = new CertPresenter(this);
        certPresenter.setOnCallBack(new CertPresenter.OnCallBack() {
            @Override
            public void addCert(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteCert(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(CertDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void updateCert(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllCerts(boolean isSuccess, Object object) {

            }
        });
    }
}
