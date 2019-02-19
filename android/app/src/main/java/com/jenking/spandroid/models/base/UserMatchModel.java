package com.jenking.spandroid.models.base;

public class UserMatchModel {
    public String id;
    public String user_id;
    public String match_id;
    public String user_match_status;
    public String user_match_score;
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

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getUser_match_status() {
        return user_match_status;
    }

    public void setUser_match_status(String user_match_status) {
        this.user_match_status = user_match_status;
    }

    public String getUser_match_score() {
        return user_match_score;
    }

    public void setUser_match_score(String user_match_score) {
        this.user_match_score = user_match_score;
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
        return "UserMatchModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", match_id='" + match_id + '\'' +
                ", user_match_status='" + user_match_status + '\'' +
                ", user_match_score='" + user_match_score + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
