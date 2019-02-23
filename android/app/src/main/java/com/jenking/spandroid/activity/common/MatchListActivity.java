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
import com.jenking.spandroid.activity.manager.ManagerMatchActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.presenter.MatchPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MatchListActivity extends BaseActivity {

    public static final int SelectMatchCode = 1002;


    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<MatchModel> datas;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private MatchPresenter matchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<MatchModel>(this,datas,R.layout.activity_match_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, MatchModel item) {
                helper.setText(R.id.match_name,item.getMatch_name());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("match_id",datas.get(position).getId());
                intent.putExtra("match_name",datas.get(position).getMatch_name());
                setResult(SelectMatchCode,intent);
                finish();
            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        matchPresenter = new MatchPresenter(this);
        matchPresenter.setOnCallBack(new MatchPresenter.OnCallBack() {
            @Override
            public void addMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllMatchs(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void updateMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteMatch(boolean isSuccess, Object object) {

            }
        });

        matchPresenter.getAllMatchs(RS.getBaseParams(this));
    }

}
