package com.jenking.spandroid.fragment.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.ComprehensiveReportIsWhatActivity;
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

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment1 extends Fragment {

    private Unbinder unbinder;

    //-------------------------------------学生区域
    @OnClick(R.id.mine_match)
    void mine_match(){
        Intent intent = new Intent(getContext(),MineMatchActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.mine_cert)
    void mine_cert(){
        Intent intent = new Intent(getContext(),MineCertActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.mine_activity)
    void mine_activity(){
        Intent intent = new Intent(getContext(),MineActivityActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.mine_moral)
    void mine_moral(){
        Intent intent = new Intent(getContext(),MineMoralActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mine_report)
    void mine_report(){
        Intent intent = new Intent(getContext(),MineReportActivity.class);
        startActivity(intent);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part1,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
}
