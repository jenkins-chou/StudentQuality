package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.SchoolModel;
import com.jenking.spandroid.presenter.SchoolPresenter;
import com.jenking.spandroid.tools.StringUtil;

import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerSchoolOperateActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @BindView(R.id.operate_tips)
    TextView operate_tips;

    @BindView(R.id.school_name)
    EditText school_name;
    @BindView(R.id.school_abstract)
    EditText school_abstract;
    @BindView(R.id.school_detail)
    EditText school_detail;
    @BindView(R.id.school_address)
    EditText school_address;
    @BindView(R.id.school_build_time)
    TextView school_build_time;

    private String select_time;
    private SchoolPresenter schoolPresenter;

    private boolean isAddData = true;//判断是否是新增
    private SchoolModel schoolModel;

    @OnClick(R.id.school_build_time)
    void select_build_time(){
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Log.e("date",date.getTime()/1000+"");
                select_time = date.getTime()/1000+"";
                school_build_time.setText(StringUtil.getStrTime(date.getTime()/1000+"","yyyy年MM月dd日 HH时mm分ss秒"));
            }
        }).setTitleText("选择比赛时间")
                .setType(new boolean[]{true,true,true,false,false,false})
                .build();
        pvTime.show();
    }
    @OnClick(R.id.submit)
    void submit(){
        String school_name_str = school_name.getText().toString();
        String school_abstract_str = school_abstract.getText().toString();
        String school_detail_str = school_detail.getText().toString();
        String school_address_str = school_address.getText().toString();
        String school_build_time_str = school_build_time.getText().toString();
        if (!StringUtil.isNotEmpty(school_name_str)
                ||!StringUtil.isNotEmpty(school_abstract_str)
                ||!StringUtil.isNotEmpty(school_detail_str)
                ||!StringUtil.isNotEmpty(school_address_str)
                ||!StringUtil.isNotEmpty(school_build_time_str)) {
            Toast.makeText(this, "请完善学校信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (schoolPresenter!=null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("school_name",school_name_str);
            params.put("school_abstract",school_abstract_str);
            params.put("school_detail",school_detail_str);
            params.put("school_address",school_address_str);
            params.put("school_build_time",school_build_time_str);
            params.put("create_time",StringUtil.getTime());
            params.put("remark","");
            Log.e("params",params.toString());
            if (isAddData){
                schoolPresenter.addSchool(params);
            }else{
                params.put("id",schoolModel.getId());
                schoolPresenter.updateSchool(params);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_school_operate);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent!=null&&StringUtil.isNotEmpty(intent.getStringExtra("model"))){
            //表明是修改
            operate_tips.setText("当前操作：修改");
            isAddData = false;
            String schoolJson = intent.getStringExtra("model");
            schoolModel = new Gson().fromJson(schoolJson,SchoolModel.class);
            if (schoolModel!=null){
                school_name.setText(schoolModel.getSchool_name());
                school_abstract.setText(schoolModel.getSchool_abstract());
                school_detail.setText(schoolModel.getSchool_detail());
                school_address.setText(schoolModel.getSchool_address());
                school_build_time.setText(schoolModel.getSchool_build_time());
            }

        }else{
            operate_tips.setText("当前操作：新增");
            isAddData = true;
        }

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
                Log.e("addSchool",""+isSuccess);
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getStatus(),"200")){
                            Toast.makeText(ManagerSchoolOperateActivity.this, "新增成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ManagerSchoolOperateActivity.this, "不能添加重复名称的学校", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }

            @Override
            public void updateSchool(boolean isSuccess, Object object) {
                if (isSuccess){
                    Toast.makeText(ManagerSchoolOperateActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void deleteSchool(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllSchool(boolean isSuccess, Object object) {

            }
        });

    }
}
