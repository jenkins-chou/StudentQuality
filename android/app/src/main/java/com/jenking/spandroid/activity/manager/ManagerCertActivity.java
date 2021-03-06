package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.CertDetailActivity;
import com.jenking.spandroid.activity.common.CertOperateActivity;
import com.jenking.spandroid.activity.common.MatchDetailActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CertificateModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CertPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerCertActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<CertificateModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private CertPresenter certPresenter;

    @OnClick(R.id.add_cert)
    void add_cert(){
        Intent intent = new Intent(this, CertOperateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_cert);
    }
    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<CertificateModel>(this,datas,R.layout.activity_manager_cert_item) {
            @Override
            protected void convert(BaseViewHolder helper, CertificateModel item) {
                helper.setText(R.id.cert_name,item.getCertificate_name());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ManagerCertActivity.this, CertDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        certPresenter = new CertPresenter(this);
        certPresenter.setOnCallBack(new CertPresenter.OnCallBack() {
            @Override
            public void addCert(boolean isSuccess, Object object) {
            }

            @Override
            public void deleteCert(boolean isSuccess, Object object) {

            }

            @Override
            public void updateCert(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllCerts(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (certPresenter!=null){
            certPresenter.getAllCerts(RS.getBaseParams(this));
        }
    }
}
