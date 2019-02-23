package com.jenking.spandroid.models.impl;

import com.jenking.spandroid.models.base.UserActiModel;

public class UserActivityDetail extends UserActiModel {
    public String activity_name;
    public String activity_time;
    public String activity_leader;
    public String activity_abstract;
    public String activity_detail;
    public String activity_stunum;
    public String activity_status;
    public String activity_score;

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time = activity_time;
    }

    public String getActivity_leader() {
        return activity_leader;
    }

    public void setActivity_leader(String activity_leader) {
        this.activity_leader = activity_leader;
    }

    public String getActivity_abstract() {
        return activity_abstract;
    }

    public void setActivity_abstract(String activity_abstract) {
        this.activity_abstract = activity_abstract;
    }

    public String getActivity_detail() {
        return activity_detail;
    }

    public void setActivity_detail(String activity_detail) {
        this.activity_detail = activity_detail;
    }

    public String getActivity_stunum() {
        return activity_stunum;
    }

    public void setActivity_stunum(String activity_stunum) {
        this.activity_stunum = activity_stunum;
    }

    public String getActivity_status() {
        return activity_status;
    }

    public void setActivity_status(String activity_status) {
        this.activity_status = activity_status;
    }

    public String getActivity_score() {
        return activity_score;
    }

    public void setActivity_score(String activity_score) {
        this.activity_score = activity_score;
    }

    @Override
    public String toString() {
        return "UserActivityDetail{" +
                "activity_name='" + activity_name + '\'' +
                ", activity_time='" + activity_time + '\'' +
                ", activity_leader='" + activity_leader + '\'' +
                ", activity_abstract='" + activity_abstract + '\'' +
                ", activity_detail='" + activity_detail + '\'' +
                ", activity_stunum='" + activity_stunum + '\'' +
                ", activity_status='" + activity_status + '\'' +
                ", activity_score='" + activity_score + '\'' +
                '}';
    }
}
