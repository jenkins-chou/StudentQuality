package com.jenking.spandroid.activity.manager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.spandroid.R;
import com.jenking.spandroid.activity.common.BaseActivity;
import com.jenking.spandroid.activity.common.ClassListActivity;
import com.jenking.spandroid.api.RS;
import com.jenking.spandroid.models.base.ResultModel;
import com.jenking.spandroid.models.base.UserModel;
import com.jenking.spandroid.presenter.UserPresenter;
import com.jenking.spandroid.tools.AccountTool;
import com.jenking.spandroid.tools.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerReportActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.class_name)
    TextView class_name;



    private String select_school_id;
    private String select_school_name;
    private String select_college_id;
    private String select_college_name;
    private String select_class_id;
    private String select_class_name;

    private List<UserModel> datas;
    private List<List<UserModel>> classGroupList;
    private Map<String,List<UserModel>> classGroupMap;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private UserPresenter userPresenter;

    @OnClick(R.id.select_class)
    void select_class(){
        Intent intent = new Intent(this,ClassListActivity.class);
        startActivityForResult(intent,ClassListActivity.SelectClassCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report);
    }

    @Override
    public void initData() {
        super.initData();
        classGroupMap = new HashMap<>();
        datas = new ArrayList<>();
        classGroupList = new ArrayList<List<UserModel>>();
        baseRecyclerAdapter = new BaseRecyclerAdapter<List<UserModel>>(this, classGroupList,R.layout.activity_manager_report_group_item) {

            @Override
            protected void convert(BaseViewHolder helper, final List<UserModel> item) {

                if (item!=null&&item.size()>0&&item.get(0)!=null){
                    helper.setText(R.id.class_name,item.get(0).getSchool_name()+"--"+item.get(0).getCollege_name()+"--"+item.get(0).getClass_name());

                    RecyclerView recyclerView = helper.getView(R.id.item_recyclerView);
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

                    BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<UserModel>(ManagerReportActivity.this,item,R.layout.activity_manager_report_item) {
                        @Override
                        protected void convert(BaseViewHolder helper, UserModel item) {
                            TextView usertype_boy = helper.getView(R.id.usertype_boy);
                            TextView usertype_girl = helper.getView(R.id.usertype_girl);
                            usertype_boy.setVisibility(View.GONE);
                            usertype_girl.setVisibility(View.GONE);
                            helper.setText(R.id.username,item.getRealname());
                            if (StringUtil.isEquals(item.getSex(),"男")){
                                usertype_boy.setText("男");
                                usertype_boy.setBackgroundColor(getResources().getColor(R.color.main_color));
                                usertype_boy.setVisibility(View.VISIBLE);
                            }else if (StringUtil.isEquals(item.getSex(),"女")){
                                usertype_boy.setText("女");
                                usertype_boy.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                usertype_boy.setVisibility(View.VISIBLE);
                            }else{
                                usertype_boy.setText("男");
                                usertype_boy.setBackgroundColor(getResources().getColor(R.color.main_color));
                                usertype_boy.setVisibility(View.VISIBLE);
                            }
                        }
                    };

                    adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(ManagerReportActivity.this,ManagerReportListActivity.class);
                            intent.putExtra("user_id",item.get(position).getId());
                            intent.putExtra("model",new Gson().toJson(item.get(position)));
                            startActivity(intent);
                        }
                    });
                    adapter.openLoadAnimation(false);

                    recyclerView.setAdapter(adapter);
                }

            }
        };

        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(baseRecyclerAdapter);

        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {
            @Override
            public void login(boolean isSuccess, Object object) {

            }

            @Override
            public void addUser(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachers(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachersByCollege(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllStudent(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        changeData(datas);
//                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }

            @Override
            public void getStudentByClass(boolean isSuccess, Object object) {
                if (isSuccess&&object!=null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getStatus(),"200")){
                        datas = resultModel.getData()!=null?resultModel.getData():datas;
                        changeData(datas);
//                        baseRecyclerAdapter.setData(datas);
                    }
                }
            }
        });
    }

    private void changeData(List<UserModel> source){
        if (source==null)return;
        classGroupMap.clear();
        classGroupList.clear();
        for (int i = 0;i<source.size();i++){
            if (StringUtil.isNotEmpty(source.get(i).getClass_name())){
                List<UserModel> group = classGroupMap.get(source.get(i).getClass_name());
                if (null != group){
                    group.add(source.get(i));
                }else{
                    List<UserModel> newGroup = new ArrayList<>();
                    newGroup.add(source.get(i));
                    classGroupMap.put(source.get(i).getClass_name(),newGroup);
                }
            }
        }

        Iterator iterator = classGroupMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            classGroupList.add((List<UserModel>) entry.getValue());

            Log.e("classGroupList",""+entry.getValue());
        }

        baseRecyclerAdapter.setData(classGroupList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userPresenter!=null){
            if (!StringUtil.isNotEmpty(select_class_id)){
                userPresenter.getAllStudent(RS.getBaseParams(this));
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ClassListActivity.SelectClassCode:
                if (data != null) {
                    select_school_id = data.getStringExtra("school_id");
                    select_school_name = data.getStringExtra("school_name");
                    select_college_id = data.getStringExtra("college_id");
                    select_college_name = data.getStringExtra("college_name");
                    select_class_id = data.getStringExtra("class_id");
                    select_class_name = data.getStringExtra("class_name");
                    class_name.setText(select_school_name +"--"+ select_college_name+"--"+select_class_name);

                    if (userPresenter!=null){
                        Map<String,String> params = RS.getBaseParams(this);
                        params.put("class_id",select_class_id);
                        params.put("college_id",select_college_id);
                        params.put("school_id",select_school_id);
                        userPresenter.getStudentByClass(params);
                    }
                }
                break;
        }
    }


}
