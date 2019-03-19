package com.jenking.spandroid.activity.student;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.CourseDetailActivity;
import com.jenking.spandroid.activity.common.UserActiDetailActivity;
import com.jenking.spandroid.activity.common.UserCertDetailActivity;
import com.jenking.spandroid.activity.common.UserMatchDetailActivity;
import com.jenking.spandroid.activity.common.UserMoralDetailActivity;
import com.jenking.spandroid.activity.manager.ManagerReportListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.models.impl.UserActivityDetail;
import com.jenking.spandroid.models.impl.UserCertDetail;
import com.jenking.spandroid.models.impl.UserCourseDetail;
import com.jenking.spandroid.models.impl.UserMatchDetail;
import com.jenking.spandroid.models.impl.UserMoralDetail;
import com.jenking.spandroid.presenter.UserActivityPresenter;
import com.jenking.spandroid.presenter.UserCertPresenter;
import com.jenking.spandroid.presenter.UserCoursePresenter;
import com.jenking.spandroid.presenter.UserMatchPresenter;
import com.jenking.spandroid.presenter.UserMoralPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.DocUtils;
import com.jenking.spandroid.tools.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineReportActivity extends BaseActivity {

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String user_id;

    private boolean isLoadCourse = false;
    private boolean isLoadMatch = false;
    private boolean isLoadCert = false;
    private boolean isLoadActivity = false;
    private boolean isLoadMoral = false;

    private List<UserCourseDetail> userCourseDetails;
    private List<UserMatchDetail> userMatchDetails;
    private List<UserCertDetail> userCertDetails;
    private List<UserActivityDetail> userActivityDetails;
    private List<UserMoralDetail> userMoralDetails;

    private BaseRecyclerAdapter courseAdapter;
    private BaseRecyclerAdapter matchAdapter;
    private BaseRecyclerAdapter certAdapter;
    private BaseRecyclerAdapter actiAdapter;
    private BaseRecyclerAdapter moralAdapter;

    private UserCoursePresenter userCoursePresenter;
    private UserMatchPresenter userMatchPresenter;
    private UserCertPresenter userCertPresenter;
    private UserActivityPresenter userActivityPresenter;
    private UserMoralPresenter userMoralPresenter;

    private int courseScoreSum = 0;
    private int courseScoreCount = 0;
    private int courseAverageScore = 0;

    private float coursePointSum = 0;
    private int coursePointCount = 0;
    private float coursePointScore = 0;

    private int matchFraction = 0;
    private int certFraction = 0;
    private int actiFraction = 0;
    private int moralFraction = 0;

    @BindView(R.id.course_average)
    TextView course_average;
    @BindView(R.id.course_point)
    TextView course_point;

    @BindView(R.id.match_score)
    TextView match_score;
    @BindView(R.id.cert_score)
    TextView cert_score;
    @BindView(R.id.acti_score)
    TextView acti_score;
    @BindView(R.id.moral_score)
    TextView moral_score;
    @BindView(R.id.all_score)
    TextView all_score;

    @BindView(R.id.course_rv)
    RecyclerView course_rv;
    @BindView(R.id.match_rv)
    RecyclerView match_rv;
    @BindView(R.id.cert_rv)
    RecyclerView cert_rv;
    @BindView(R.id.acti_rv)
    RecyclerView acti_rv;
    @BindView(R.id.moral_rv)
    RecyclerView moral_rv;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.print_report)
    void print_report(){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 10001);
            }else{
                saveReport();
            }
        }else {
            saveReport();
        }
    }

    void saveReport(){
        if (isLoadActivity
                &&isLoadCert
                &&isLoadCourse
                &&isLoadMatch
                &&isLoadMoral){


            String json = getIntent().getStringExtra("model");
            UserModel userModel = new Gson().fromJson(json,UserModel.class);
            Map<String,String> map = new HashMap<>();
            map.put("$Name$",userModel.getRealname());
            map.put("$College$",userModel.getCollege_name());
            map.put("$School$",userModel.getSchool_name());
            map.put("$Class$",userModel.getClass_name());
            map.put("$Sex$",userModel.getSex());

            map.put("$FinalScore$",all_score.getText().toString());
            map.put("$AverageScore$",course_average.getText().toString());
            map.put("$AveragePoint$",course_point.getText().toString());
            map.put("$MatchScore$",match_score.getText().toString());
            map.put("$CertScore$",cert_score.getText().toString());
            map.put("$ActivityScore$",acti_score.getText().toString());
            map.put("$MoralScore$",moral_score.getText().toString());
            map.put("$Date$",StringUtil.getStrTime(StringUtil.getTime()));

            DocUtils.save(this,map,userModel.getRealname());

            Toast.makeText(this, "文件已保存至 【文件管理根目录/学生综合素质管理系统/学生报告.doc】", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_report);
    }

    @Override
    public void initData() {
        super.initData();
        if (AccountTool.isLogin(this)&&AccountTool.getLoginUser(this)!=null){
            user_id = AccountTool.getLoginUser(this).getId();
            initUserCourse();
            initUserMatch();
            initUserCert();
            initUserActi();
            initUserMoral();

            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",user_id);

            if (userCoursePresenter!=null){
                userCoursePresenter.getCoursesByUserId(params);
            }

            if (userMatchPresenter!=null){
                userMatchPresenter.getUserMatchByUserId(params);
            }

            if (userCertPresenter!=null){
                userCertPresenter.getUserCertByUserId(params);
            }

            if (userActivityPresenter!=null){
                userActivityPresenter.getUserActivityByUserId(params);
            }

            if (userMoralPresenter!=null){
                userMoralPresenter.getUserMoralByUserId(params);
            }
        }else{
            Log.e("what?","????");
        }
    }

    private void initUserCourse(){
        userCourseDetails = new ArrayList<>();
        courseAdapter = new BaseRecyclerAdapter<UserCourseDetail>(this,userCourseDetails,R.layout.activity_manager_report_course_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserCourseDetail item) {
                helper.setText(R.id.course_name,item.getCourse_name());
                if (StringUtil.isNotEmpty(item.getUser_course_score())){
                    helper.setText(R.id.user_course_score,"成绩:"+item.getUser_course_score()+"分");
                    if (StringUtil.isNumber(item.getUser_course_score())){
                        Integer score = Integer.parseInt(item.getUser_course_score());
                        if (score<60){
                            helper.setText(R.id.achievement_point,"绩点 0 ");
                            helper.setText(R.id.user_course_score_status,"不及格");
                        }else{
                            helper.setText(R.id.achievement_point,"绩点 "+(float)(score-50)/10);
                        }
                    }
                }else{
                    helper.setText(R.id.user_course_score,"成绩:未公布");
                }
            }
        };
        courseAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,CourseDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userCourseDetails.get(position)));
                startActivity(intent);
            }
        });
        courseAdapter.openLoadAnimation(false);
        course_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        course_rv.setAdapter(courseAdapter);

        userCoursePresenter = new UserCoursePresenter(this);
        userCoursePresenter.setOnCallBack(new UserCoursePresenter.OnCallBack() {
            @Override
            public void getCoursesByClassId(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteByClassIdAndCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void addCourseTypeClass(boolean isSuccess, Object object) {

            }

            @Override
            public void getCoursesByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userCourseDetails = resultModel.getData()!=null?resultModel.getData():userCourseDetails;
                        courseAdapter.setData(userCourseDetails);
                        isLoadCourse = true;
                    }
                }
                statistics();
            }

            @Override
            public void deleteByUserIdAndCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void addCourseTypeUser(boolean isSuccess, Object object) {

            }

            @Override
            public void getUserByCourseId(boolean isSuccess, Object object) {

            }

            @Override
            public void excute(boolean isSuccess, Object object) {

            }
        });
    }

    private void initUserMatch(){
        userMatchDetails = new ArrayList<>();
        matchAdapter = new BaseRecyclerAdapter<UserMatchDetail>(this,userMatchDetails,R.layout.activity_manager_report_match_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserMatchDetail item) {
                helper.setText(R.id.match_name,item.getMatch_name());
                helper.setText(R.id.user_match_score,"加分:"+item.getUser_match_score()+"分");
            }
        };
        matchAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,UserMatchDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userMatchDetails.get(position)));
                startActivity(intent);
            }
        });
        matchAdapter.openLoadAnimation(false);
        match_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        match_rv.setAdapter(matchAdapter);

        userMatchPresenter = new UserMatchPresenter(this);
        userMatchPresenter.setOnCallBack(new UserMatchPresenter.OnCallBack() {
            @Override
            public void getUserMatchByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userMatchDetails = resultModel.getData()!=null?resultModel.getData():userMatchDetails;
                        matchAdapter.setData(userMatchDetails);
                        isLoadMatch = true;
                    }
                }
                statistics();
            }

            @Override
            public void addUserMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserMatch(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserMatch(boolean isSuccess, Object object) {

            }
        });
    }

    private void initUserCert(){
        userCertDetails = new ArrayList<>();
        certAdapter = new BaseRecyclerAdapter<UserCertDetail>(this,userCertDetails,R.layout.activity_manager_report_cert_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserCertDetail item) {
                helper.setText(R.id.cert_name,item.getCertificate_name());
                helper.setText(R.id.user_cert_score,"加分:"+item.getCertificate_score()+"分");
            }
        };
        certAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,UserCertDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userCertDetails.get(position)));
                startActivity(intent);
            }
        });
        certAdapter.openLoadAnimation(false);
        cert_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        cert_rv.setAdapter(certAdapter);

        userCertPresenter = new UserCertPresenter(this);
        userCertPresenter.setOnCallBack(new UserCertPresenter.OnCallBack() {
            @Override
            public void getUserCertByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userCertDetails = resultModel.getData()!=null?resultModel.getData():userMatchDetails;
                        certAdapter.setData(userCertDetails);
                        isLoadCert = true;
                    }
                }
                statistics();
            }

            @Override
            public void addUserCert(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserCert(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserCert(boolean isSuccess, Object object) {

            }
        });
    }

    private void initUserActi(){
        userActivityDetails = new ArrayList<>();
        actiAdapter = new BaseRecyclerAdapter<UserActivityDetail>(this,userActivityDetails,R.layout.activity_manager_report_acti_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserActivityDetail item) {
                helper.setText(R.id.acti_name,item.getActivity_name());
                helper.setText(R.id.user_acti_score,"加分:"+item.getUser_activity_score()+"分");
            }
        };
        actiAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,UserActiDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userActivityDetails.get(position)));
                startActivity(intent);
            }
        });
        actiAdapter.openLoadAnimation(false);
        acti_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        acti_rv.setAdapter(actiAdapter);

        userActivityPresenter = new UserActivityPresenter(this);
        userActivityPresenter.setOnCallBack(new UserActivityPresenter.OnCallBack() {
            @Override
            public void getUserActivityByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userActivityDetails = resultModel.getData()!=null?resultModel.getData():userActivityDetails;
                        actiAdapter.setData(userActivityDetails);
                        isLoadActivity = true;
                    }
                }
                statistics();
            }

            @Override
            public void addUserActivity(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserActivity(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserActivity(boolean isSuccess, Object object) {

            }
        });
    }

    private void initUserMoral(){
        userMoralDetails = new ArrayList<>();
        moralAdapter = new BaseRecyclerAdapter<UserMoralDetail>(this,userMoralDetails,R.layout.activity_manager_report_moral_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserMoralDetail item) {
                helper.setText(R.id.moral_name,item.getMoral_name());

                TextView add_type = helper.getView(R.id.add_type);
                TextView reduce_type = helper.getView(R.id.reduce_type);
                add_type.setVisibility(View.GONE);
                reduce_type.setVisibility(View.GONE);
                if (StringUtil.isEquals(item.getMoral_type(),"加分")){
                    add_type.setText("加分:"+item.getMoral_score()+"分");
                    add_type.setVisibility(View.VISIBLE);
                }else if (StringUtil.isEquals(item.getMoral_type(),"减分")){
                    reduce_type.setText("减分:"+item.getMoral_score()+"分");
                    reduce_type.setVisibility(View.VISIBLE);
                }
            }
        };
        moralAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MineReportActivity.this,UserMoralDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(userMoralDetails.get(position)));
                startActivity(intent);
            }
        });
        moralAdapter.openLoadAnimation(false);
        moral_rv.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        moral_rv.setAdapter(moralAdapter);

        userMoralPresenter = new UserMoralPresenter(this);
        userMoralPresenter.setOnCallBack(new UserMoralPresenter.OnCallBack() {
            @Override
            public void getUserMoralByUserId(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        userMoralDetails = resultModel.getData()!=null?resultModel.getData():userMoralDetails;
                        moralAdapter.setData(userMoralDetails);
                        isLoadMoral = true;
                    }
                }
                statistics();
            }

            @Override
            public void addUserMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUserMoral(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUserMoral(boolean isSuccess, Object object) {

            }
        });
    }

    private void statistics(){
        if (isLoadCourse&&isLoadMatch&&isLoadCert&&isLoadActivity&&isLoadMoral){
            if (userCourseDetails!=null){
                for (UserCourseDetail userCourseDetail:userCourseDetails){
                    String user_course_score = userCourseDetail.getUser_course_score();
                    if (StringUtil.isNotEmpty(user_course_score)&&StringUtil.isNumber(user_course_score)){
                        int score =  Integer.parseInt(user_course_score);
                        courseScoreSum +=score;
                        courseScoreCount++;

                        if (score>60){
                            coursePointCount++;
                            coursePointSum+=(float)(score-50)/10;
                        }
                    }
                }
                if (courseScoreCount>0){
                    courseAverageScore = courseScoreSum/courseScoreCount;
                    course_average.setText(courseAverageScore+"");
                }
                if (coursePointCount>0){
                    coursePointScore = coursePointSum/coursePointCount;
                    course_point.setText(coursePointScore+"");
                }
            }

            if (userMatchDetails!=null){
                for (UserMatchDetail userMatchDetail:userMatchDetails){
                    String user_match_score = userMatchDetail.getUser_match_score();
                    if (StringUtil.isNumber(user_match_score)){
                        Integer score = Integer.parseInt(user_match_score);
                        matchFraction+=score;
                    }
                }
                match_score.setText(matchFraction+"");
            }

            if (userCertDetails!=null){
                for (UserCertDetail userCertDetail:userCertDetails){
                    String user_cert_score = userCertDetail.getCertificate_score();
                    if (StringUtil.isNumber(user_cert_score)){
                        Integer score = Integer.parseInt(user_cert_score);
                        certFraction+=score;
                    }
                }
                cert_score.setText(certFraction+"");
            }

            if (userActivityDetails!=null){
                for (UserActivityDetail userActivityDetail:userActivityDetails){
                    String user_activity_score = userActivityDetail.getUser_activity_score();
                    if (StringUtil.isNumber(user_activity_score)){
                        Integer score = Integer.parseInt(user_activity_score);
                        actiFraction+=score;
                    }
                }
                acti_score.setText(actiFraction+"");
            }

            if (userMoralDetails!=null){
                for (UserMoralDetail userMoralDetail:userMoralDetails){
                    String user_moral_score = userMoralDetail.getMoral_score();
                    if (StringUtil.isNumber(user_moral_score)){
                        Integer score = Integer.parseInt(user_moral_score);
                        if (StringUtil.isEquals(userMoralDetail.getMoral_type(),"加分")){
                            moralFraction+=score;
                        }else if (StringUtil.isEquals(userMoralDetail.getMoral_type(),"减分")){
                            moralFraction-=score;
                        }
                    }
                }
                moral_score.setText(moralFraction+"");
            }

            float all_score_f = courseAverageScore * (float)0.7 + (float)(matchFraction+certFraction+actiFraction+moralFraction)*0.3f;
            int  scale  =  2;//设置位数 
               int  roundingMode  =  4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
               BigDecimal bd  =  new  BigDecimal((double)all_score_f);
               bd  =  bd.setScale(scale,roundingMode);
            all_score_f  =  bd.floatValue();
            
            all_score.setText(all_score_f+"");
        }
    }

}
