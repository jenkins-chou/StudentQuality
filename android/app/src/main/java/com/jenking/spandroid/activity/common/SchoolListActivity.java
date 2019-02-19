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
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.SchoolModel;
import com.jenking.spandroid.presenter.SchoolPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SchoolListActivity extends BaseActivity {

    public static final int SelectSchoolCode = 10001;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<SchoolModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    private SchoolPresenter schoolPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);
    }


    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<SchoolModel>(this,datas,R.layout.activity_school_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, SchoolModel item) {
                helper.setText(R.id.school_name,item.getSchool_name());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);

        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                if (intent!=null){
                    intent.putExtra("school_id",datas.get(position).getId());
                    intent.putExtra("school_name",datas.get(position).getSchool_name());
                    setResult(SelectSchoolCode,intent);
                    finish();
                }
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

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

            }

            @Override
            public void getAllSchool(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }
        });

        schoolPresenter.getAllSchool(RS.getBaseParams(this));
    }
}
