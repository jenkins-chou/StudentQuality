package com.jenking.spandroid.models.base;

public class UserActiModel {
    public String id;
    public String user_id;
    public String activity_id;
    public String user_activity_status;
    public String user_activity_score;
    public String create_time;
    public String remark;
    public String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getUser_activity_status() {
        return user_activity_status;
    }

    public void setUser_activity_status(String user_activity_status) {
        this.user_activity_status = user_activity_status;
    }

    public String getUser_activity_score() {
        return user_activity_score;
    }

    public void setUser_activity_score(String user_activity_score) {
        this.user_activity_score = user_activity_score;
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
        return "UserActiModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", activity_id='" + activity_id + '\'' +
                ", user_activity_status='" + user_activity_status + '\'' +
                ", user_activity_score='" + user_activity_score + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
