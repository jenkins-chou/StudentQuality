package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.manager.ManagerMatchActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.presenter.MatchPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MatchDetailActivity extends BaseActivity {

    private MatchModel matchModel;
    private MatchPresenter matchPresenter;

    @BindView(R.id.match_name)
    TextView match_name;
    @BindView(R.id.match_time)
    TextView match_time;
    @BindView(R.id.match_abstract)
    TextView match_abstract;
    @BindView(R.id.match_detail)
    TextView match_detail;
    @BindView(R.id.match_sponsor)
    TextView match_sponsor;
    @BindView(R.id.match_level)
    TextView match_level;
    @BindView(R.id.match_status)
    TextView match_status;
    @BindView(R.id.remark)
    TextView remark;
    
    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify_match)
    void modify_match(){
        if (matchModel!=null){
            Intent intent = new Intent(this, MatchOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(matchModel));
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.delete_match)
    void delete_match(){
        CommonTipsDialog.create(this,"温馨提示","确认要删除吗？",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        if (matchPresenter!=null&&matchModel!=null){
                            Map<String,String> params = RS.getBaseParams(MatchDetailActivity.this);
                            params.put("id",matchModel.getId());
                            matchPresenter.deleteMatch(params);
                        }

                    }
                }).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            String json = intent.getStringExtra("model");
            matchModel = new Gson().fromJson(json, MatchModel.class);

            if (matchModel!=null){
                match_name.setText(matchModel.getMatch_name());
                match_time.setText(matchModel.getMatch_time());
                match_abstract.setText(matchModel.getMatch_abstract());
                match_detail.setText(matchModel.getMatch_detail());
                match_sponsor.setText(matchModel.getMatch_sponsor());
                match_level.setText(matchModel.getMatch_level());
                match_status.setText(matchModel.getMatch_status());
                remark.setText(matchModel.getRemark());
            }
        }

        matchPresenter = new MatchPresenter(this);
        matchPresenter.setOnCallBack(new MatchPresenter.OnCallBack() {
            @Override
            public void addMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllMatchs(boolean isSuccess, Object object) {

            }

            @Override
            public void updateMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteMatch(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(MatchDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}
