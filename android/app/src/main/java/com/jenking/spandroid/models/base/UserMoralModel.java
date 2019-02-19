package com.jenking.spandroid.models.base;

public class UserMoralModel {
    public String id;
    public String user_id;
    public String moral_id;
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

    public String getMoral_id() {
        return moral_id;
    }

    public void setMoral_id(String moral_id) {
        this.moral_id = moral_id;
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
        return "UserMoralModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", moral_id='" + moral_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
