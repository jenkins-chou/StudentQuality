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
import com.jenking.spandroid.models.base.TermModel;
import com.jenking.spandroid.presenter.SchoolPresenter;
import com.jenking.spandroid.presenter.TermPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TermListActivity extends BaseActivity {

    public static final int SelectTermCode = 40001;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<TermModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;

    private TermPresenter termPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<TermModel>(this, datas, R.layout.activity_school_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, TermModel item) {
                helper.setText(R.id.school_name, item.getTerm_name());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);

        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                if (intent != null) {
                    intent.putExtra("term_id", datas.get(position).getId());
                    intent.putExtra("term_name", datas.get(position).getTerm_name());
                    setResult(SelectTermCode, intent);
                    finish();
                }
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
        recyclerView.setAdapter(baseRecyclerAdapter);

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

            }

            @Override
            public void getAllTerms(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }
        });
        termPresenter.getAllTerms(RS.getBaseParams(this));
    }


}
