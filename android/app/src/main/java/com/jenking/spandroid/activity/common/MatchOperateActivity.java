package com.jenking.spandroid.activity.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonBottomListDialog;
import com.jenking.spandroid.models.base.MatchModel;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.MatchPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MatchOperateActivity extends BaseActivity {
    private boolean isAddData = true;
    private MatchModel matchModel;
    private MatchPresenter matchPresenter;

    private List<String> matchLevelList;
    private List<String> matchStatusList;
    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.match_name)
    EditText match_name;
    @BindView(R.id.match_time)
    EditText match_time;
    @BindView(R.id.match_abstract)
    EditText match_abstract;
    @BindView(R.id.match_detail)
    EditText match_detail;
    @BindView(R.id.match_sponsor)
    EditText match_sponsor;
    @BindView(R.id.match_level)
    TextView match_level;
    @BindView(R.id.match_status)
    TextView match_status;
    @BindView(R.id.remark)
    EditText remark;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.match_level)
    void select_match_level(){
        CommonBottomListDialog commonBottomListDialog = new CommonBottomListDialog(this,"比赛级别",matchLevelList,"",false) {
            @Override
            protected void setOnItemClickListener(String value) {
                match_level.setText(value);
            }
        };
        commonBottomListDialog.show();
    }

    @OnClick(R.id.match_status)
    void select_match_status(){
        CommonBottomListDialog commonBottomListDialog = new CommonBottomListDialog(this,"比赛状态",matchStatusList,"",false) {
            @Override
            protected void setOnItemClickListener(String value) {
                match_status.setText(value);
            }
        };
        commonBottomListDialog.show();
    }

    @OnClick(R.id.submit)
    void submit(){
        String match_name_str = match_name.getText().toString();
        String match_level_str = match_level.getText().toString();
        if (!StringUtil.isNotEmpty(match_level_str)
                ||!StringUtil.isNotEmpty(match_name_str)){
            Toast.makeText(this, "比赛名称和比赛级别必填", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (matchPresenter!=null){

                Map<String,String> params = RS.getBaseParams(this);
                params.put("match_name",match_name_str);
                params.put("match_time",match_time.getText().toString());
                params.put("match_abstract",match_abstract.getText().toString());
                params.put("match_detail",match_detail.getText().toString());
                params.put("match_sponsor",match_sponsor.getText().toString());
                params.put("match_level",match_level.getText().toString());
                params.put("match_status",match_status.getText().toString());
                params.put("remark",remark.getText().toString());

                if (isAddData){
                    params.put("create_time",StringUtil.getTime());
                    matchPresenter.addMatch(params);
                }else{
                    if (matchModel!=null){
                        params.put("id",matchModel.getId());
                        matchPresenter.updateMatch(params);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_operate);
    }


    @Override
    public void initData() {
        super.initData();
        matchLevelList = new ArrayList<>();
        matchLevelList.add("国际比赛");
        matchLevelList.add("全国");
        matchLevelList.add("省级");
        matchLevelList.add("市级");
        matchLevelList.add("县级");
        matchLevelList.add("校级");
        matchLevelList.add("院级");
        matchLevelList.add("系/班级");

        matchStatusList = new ArrayList<>();
        matchStatusList.add("报名中");
        matchStatusList.add("比赛中");
        matchStatusList.add("比赛完毕");
        matchStatusList.add("公布成绩");

        Intent intent = getIntent();
        if (intent!=null&& StringUtil.isNotEmpty(intent.getStringExtra("model"))) {
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;

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

        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

        matchPresenter = new MatchPresenter(this);
        matchPresenter.setOnCallBack(new MatchPresenter.OnCallBack() {
            @Override
            public void addMatch(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(MatchOperateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(MatchOperateActivity.this, "已存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void getAllMatchs(boolean isSuccess, Object object) {

            }

            @Override
            public void updateMatch(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(MatchOperateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void deleteMatch(boolean isSuccess, Object object) {

            }
        });
    }
}
