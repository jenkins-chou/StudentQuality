package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerCertActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.CertificateModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CertPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertListActivity extends BaseActivity {

    public static final int SelectCertCode = 2002;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<CertificateModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private CertPresenter certPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cert_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<CertificateModel>(this,datas,R.layout.activity_cert_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, CertificateModel item) {
                helper.setText(R.id.cert_name,item.getCertificate_name());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("cert_id",datas.get(position).getId());
                intent.putExtra("cert_name",datas.get(position).getCertificate_name());
                setResult(SelectCertCode,intent);
                finish();
            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
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

        certPresenter.getAllCerts(RS.getBaseParams(this));
    }

}
