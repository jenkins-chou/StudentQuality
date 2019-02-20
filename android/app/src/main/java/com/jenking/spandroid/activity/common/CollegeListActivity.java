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
import com.jenking.spandroid.models.base.CollegeModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.CollegePresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CollegeListActivity extends BaseActivity {

    public static final int SelectCollegeCode = 20001;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<CollegeModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private CollegePresenter collegePresenter;

    @OnClick(R.id.select_school)
    void select_school(){
        Intent intent = new Intent(this,SchoolListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<CollegeModel>(this,datas,R.layout.activity_college_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, CollegeModel item) {
                helper.setText(R.id.college_name,item.getCollege_name());
                helper.setText(R.id.school_name,item.getSchool_name());
            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                if (intent!=null){
                    intent.putExtra("college_id",datas.get(position).getId());
                    intent.putExtra("college_name",datas.get(position).getSchool_name());
                    setResult(SelectCollegeCode,intent);
                    finish();
                }
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        collegePresenter = new CollegePresenter(this);
        collegePresenter.setOnCallBack(new CollegePresenter.OnCallBack() {
            @Override
            public void addCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllCollege(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void updateCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteCollege(boolean isSuccess, Object object) {

            }
        });
        collegePresenter.getAllCollege(RS.getBaseParams(this));
    }
}
