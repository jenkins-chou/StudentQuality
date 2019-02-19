package com.jenking.spandroid.models.base;

public class ActiModel {
    public String id;
    public String activity_name;
    public String activity_time;
    public String activity_leader;
    public String activity_abstract;
    public String activity_detail;
    public String activity_stunum;
    public String activity_status;
    public String activity_score;
    public String create_time;
    public String remark;
    public String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "ActiModel{" +
                "id='" + id + '\'' +
                ", activity_name='" + activity_name + '\'' +
                ", activity_time='" + activity_time + '\'' +
                ", activity_leader='" + activity_leader + '\'' +
                ", activity_abstract='" + activity_abstract + '\'' +
                ", activity_detail='" + activity_detail + '\'' +
                ", activity_stunum='" + activity_stunum + '\'' +
                ", activity_status='" + activity_status + '\'' +
                ", activity_score='" + activity_score + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
