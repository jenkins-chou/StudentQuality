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
import com.jenking.spandroid.activity.common.CourseListActivity;
import com.jenking.spandroid.activity.manager.ManagerCourseActivity;
import com.jenking.spandroid.activity.manager.ManagerCourseArrangeActivity;
import com.jenking.spandroid.activity.manager.ManagerCourseSelectedActivity;
import com.jenking.spandroid.activity.manager.ManagerScoreActivity;
import com.jenking.spandroid.activity.student.MineCourseActivity;
import com.jenking.spandroid.activity.student.MineCourseElectiveActivity;
import com.jenking.spandroid.activity.student.MineCourseObligatoryActivity;
import com.jenking.spandroid.activity.teacher.TeacherCourseArrangeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment2 extends Fragment {

    private Unbinder unbinder;
    //------------------------------学生区域
    @OnClick(R.id.mine_all_course)
    void mine_all_course(){
        Intent intent = new Intent(getContext(),MineCourseActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.mine_obligatory)
    void mine_obligatory(){
        Intent intent = new Intent(getContext(),MineCourseObligatoryActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.mine_elective)
    void mine_elective(){
        Intent intent = new Intent(getContext(),MineCourseElectiveActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.course_list_all)
    void course_list_all(){
        Intent intent = new Intent(getContext(),CourseListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.course_list_obligatory)
    void course_list_obligatory(){
        Intent intent = new Intent(getContext(),CourseListActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.course_list_elective)
    void course_list_elective(){
        Intent intent = new Intent(getContext(),CourseListActivity.class);
        startActivity(intent);
    }

    //------------------------------管理员区域

    @OnClick(R.id.manager_course)
    void manager_course(){
        Intent intent = new Intent(getContext(),ManagerCourseActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_course_enroll)
    void manager_course_enroll(){
        Intent intent = new Intent(getContext(),ManagerCourseSelectedActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_course_process)
    void manager_course_process(){
        Intent intent = new Intent(getContext(),ManagerCourseSelectedActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_course_selected)
    void manager_course_selected(){
        Intent intent = new Intent(getContext(),ManagerCourseSelectedActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.manager_score)
    void manager_score(){
        Intent intent = new Intent(getContext(),ManagerScoreActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.manager_course_arrange)
    void manager_course_arrange(){
        Intent intent = new Intent(getContext(),ManagerCourseArrangeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.teacher_course_arrange)
    void teacher_course_arrange(){
        Intent intent = new Intent(getContext(),TeacherCourseArrangeActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part2,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
}
