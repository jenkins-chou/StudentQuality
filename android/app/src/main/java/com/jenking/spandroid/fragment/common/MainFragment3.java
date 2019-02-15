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
import com.jenking.spandroid.activity.common.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainFragment3 extends Fragment {
    private Unbinder unbinder;
    @OnClick({R.id.user_header})
    void user_header(){
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_part3,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
}
