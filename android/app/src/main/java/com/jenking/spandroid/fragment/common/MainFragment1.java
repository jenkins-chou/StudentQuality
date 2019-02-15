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

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment1 extends Fragment {

    private Unbinder unbinder;
    @OnClick(R.id.mine_match)
    void mine_match(){

    }
    @OnClick(R.id.mine_cert)
    void mine_cert(){

    }
    @OnClick(R.id.mine_activity)
    void mine_activity(){

    }
    @OnClick(R.id.mine_moral)
    void mine_moral(){

    }

    @OnClick(R.id.mine_report)
    void mine_report(){

    }

    @OnClick({R.id.what_is_report1,R.id.what_is_report2})
    void whatIsReport(){
        Intent intent = new Intent(getContext(),ComprehensiveReportIsWhatActivity.class);
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
