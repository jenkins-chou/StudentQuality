package com.jenking.spandroid.fragment.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.ComprehensiveReportIsWhatActivity;
import com.jenking.spandroid.activity.common.LoginActivity;
import com.jenking.spandroid.activity.manager.ManagerActiActivity;
import com.jenking.spandroid.activity.manager.ManagerCertActivity;
import com.jenking.spandroid.activity.manager.ManagerClassActivity;
import com.jenking.spandroid.activity.manager.ManagerCollegeActivity;
import com.jenking.spandroid.activity.manager.ManagerMatchActivity;
import com.jenking.spandroid.activity.manager.ManagerMoralActivity;
import com.jenking.spandroid.activity.manager.ManagerReportActivity;
import com.jenking.spandroid.activity.manager.ManagerSchoolActivity;
import com.jenking.spandroid.activity.manager.ManagerTeacherActivity;
import com.jenking.spandroid.activity.student.MineActivityActivity;
import com.jenking.spandroid.activity.student.MineCertActivity;
import com.jenking.spandroid.activity.student.MineMatchActivity;
import com.jenking.spandroid.activity.student.MineMoralActivity;
import com.jenking.spandroid.activity.student.MineReportActivity;
import com.jenking.spandroid.dialog.CommonTipsDialog;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.tools.AccountTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment1 extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.login_tips)
    TextView login_tips;
    @BindView(R.id.login_name)
    TextView login_name;
    @BindView(R.id.student_bar)
    LinearLayout student_bar;
    @BindView(R.id.manager_bar)
    LinearLayout manager_bar;

    @OnClick(R.id.login_name)
    void login_name(){
        if (!AccountTool.isLogin(getContext())){
            Intent intent = new Intent(getContext(),LoginActivity.class);
            startActivity(intent);
        }
    }
    //-------------------------------------学生区域
    @OnClick(R.id.mine_match)
    void mine_match(){
        if (checkUserinfo()){
            Intent intent = new Intent(getContext(),MineMatchActivity.class);
            startActivity(intent);
        }
    }
    @OnClick(R.id.mine_cert)
    void mine_cert(){
        if (checkUserinfo()){
            Intent intent = new Intent(getContext(),MineCertActivity.class);
            startActivity(intent);
        }
    }
    @OnClick(R.id.mine_activity)
    void mine_activity(){
        if (checkUserinfo()){
            Intent intent = new Intent(getContext(),MineActivityActivity.class);
            startActivity(intent);
        }
    }
    @OnClick(R.id.mine_moral)
    void mine_moral(){
        if (checkUserinfo()){
            Intent intent = new Intent(getContext(),MineMoralActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.mine_report)
    void mine_report(){
        if (checkUserinfo()){
            Intent intent = new Intent(getContext(),MineReportActivity.class);
            startActivity(intent);
        }
    }

    @OnClick({R.id.what_is_report1,R.id.what_is_report2})
    void whatIsReport(){
        Intent intent = new Intent(getContext(),ComprehensiveReportIsWhatActivity.class);
        startActivity(intent);
    }


    //------------------------------------------管理员区域

    @OnClick(R.id.manager_match)
    void manager_match(){
        Intent intent = new Intent(getContext(),ManagerMatchActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_cert)
    void manager_cert(){
        Intent intent = new Intent(getContext(),ManagerCertActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_activity)
    void manager_activity(){
        Intent intent = new Intent(getContext(),ManagerActiActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_moral)
    void manager_moral(){
        Intent intent = new Intent(getContext(),ManagerMoralActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.manager_report)
    void manager_report(){
        Intent intent = new Intent(getContext(),ManagerReportActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.manager_teacher)
    void manager_teacher(){
        Intent intent = new Intent(getContext(),ManagerTeacherActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_school)
    void manager_school(){
        Intent intent = new Intent(getContext(),ManagerSchoolActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_college)
    void manager_college(){
        Intent intent = new Intent(getContext(),ManagerCollegeActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_class)
    void manager_class(){
        Intent intent = new Intent(getContext(),ManagerClassActivity.class);
        startActivity(intent);
    }

    boolean checkUserinfo(){
        if (AccountTool.isLogin(getContext())&&AccountTool.isCompleteUserInfo(getContext())){
            return true;
        }else{
            CommonTipsDialog.showTip(getContext(),"温馨提示","请前往完善您的个人信息，包括真实姓名、学校，学院和班级信息，否则将无法查询您的综合素质成绩和报告",false);
            return false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part1,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountTool.isLogin(getContext())){
            student_bar.setVisibility(View.GONE);
            manager_bar.setVisibility(View.GONE);
            String username = "";
            UserModel userModel = AccountTool.getLoginUser(getContext());
            if (userModel!=null){
                username = AccountTool.getLoginUser(getContext()).getName();
                if (userModel.getType()!=null){
                    switch (userModel.getType()){
                        case AccountTool.usertype_student:
                            student_bar.setVisibility(View.VISIBLE);
                            login_name.setText("欢迎"+username+"同学");
                            break;
                        case AccountTool.usertype_teacher:
                            login_name.setText("欢迎"+username+"教师");
                            break;
                        case AccountTool.usertype_manager:
                            manager_bar.setVisibility(View.VISIBLE);
                            login_name.setText("欢迎"+username+"管理员");
                            break;
                    }
                }
            }
        }else{
            login_name.setText("请登录");
            student_bar.setVisibility(View.GONE);
            manager_bar.setVisibility(View.GONE);
        }
    }
}
