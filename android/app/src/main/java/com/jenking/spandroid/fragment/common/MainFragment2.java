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

import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.CourseShowListActivity;
import com.jenking.spandroid.activity.manager.ManagerCourseActivity;
import com.jenking.spandroid.activity.manager.ManagerCourseArrangeActivity;
import com.jenking.spandroid.activity.manager.ManagerScoreActivity;
import com.jenking.spandroid.activity.student.MineCourseActivity;
import com.jenking.spandroid.activity.student.MineCourseElectiveActivity;
import com.jenking.spandroid.activity.student.MineCourseObligatoryActivity;
import com.jenking.spandroid.activity.student.StudentSelectCourseActivity;
import com.jenking.spandroid.activity.teacher.TeacherCourseArrangeActivity;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.tools.AccountTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment2 extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.student_bar)
    LinearLayout student_bar;
    @BindView(R.id.common_bar)
    LinearLayout common_bar;
    @BindView(R.id.teacher_bar)
    LinearLayout teacher_bar;
    @BindView(R.id.manager_bar)
    LinearLayout manager_bar;

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

    @OnClick(R.id.student_select_course)
    void student_select_course(){
        Intent intent = new Intent(getContext(),StudentSelectCourseActivity.class);
        startActivity(intent);
    }


    //-------------------------公共区域
    @OnClick(R.id.course_list_obligatory)
    void course_list_obligatory(){
        Intent intent = new Intent(getContext(),CourseShowListActivity.class);
        intent.putExtra("course_type","必修");
        startActivity(intent);
    }

    @OnClick(R.id.course_list_elective)
    void course_list_elective(){
        Intent intent = new Intent(getContext(),CourseShowListActivity.class);
        intent.putExtra("course_type","选修");
        startActivity(intent);
    }

    //------------------------------管理员区域

    @OnClick(R.id.manager_course)
    void manager_course(){
        Intent intent = new Intent(getContext(),ManagerCourseActivity.class);
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


    //-----------------------------------教师区域
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

    @Override
    public void onResume() {
        super.onResume();
        common_bar.setVisibility(View.GONE);
        student_bar.setVisibility(View.GONE);
        teacher_bar.setVisibility(View.GONE);
        manager_bar.setVisibility(View.GONE);
        if (AccountTool.isLogin(getContext())){
            String username = "";
            UserModel userModel = AccountTool.getLoginUser(getContext());
            if (userModel!=null){
                username = AccountTool.getLoginUser(getContext()).getName();
                if (userModel.getType()!=null){
                    switch (userModel.getType()){
                        case AccountTool.usertype_student:
                            common_bar.setVisibility(View.VISIBLE);
                            student_bar.setVisibility(View.VISIBLE);
                            break;
                        case AccountTool.usertype_teacher:
                            common_bar.setVisibility(View.VISIBLE);
                            teacher_bar.setVisibility(View.VISIBLE);
                            break;
                        case AccountTool.usertype_manager:
                            common_bar.setVisibility(View.VISIBLE);
                            manager_bar.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        }
    }
}
