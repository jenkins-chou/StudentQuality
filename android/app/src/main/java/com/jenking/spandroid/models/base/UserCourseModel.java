package com.jenking.spandroid.models.base;

public class UserCourseModel {
    public String id;
    public String user_id;
    public String course_id;
    public String user_course_score;
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

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getUser_course_score() {
        return user_course_score;
    }

    public void setUser_course_score(String user_course_score) {
        this.user_course_score = user_course_score;
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
        return "UserCourseModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", user_course_score='" + user_course_score + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
