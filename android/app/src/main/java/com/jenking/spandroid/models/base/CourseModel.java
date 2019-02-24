package com.jenking.spandroid.models.base;

public class CourseModel {
    public String id;
    public String course_name;
    public String course_stunum;
    public String course_abstract;
    public String course_detail;
    public String course_type;
    public String course_status;
    public String course_score;
    public String school_id;
    public String college_id;
    public String term_id;
    public String teacher_id;
    public String school_name;
    public String college_name;
    public String term_name;
    public String teacher_name;
    public String create_time;
    public String remark;
    public String del;
    public String course_time;
    public String course_address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_stunum() {
        return course_stunum;
    }

    public void setCourse_stunum(String course_stunum) {
        this.course_stunum = course_stunum;
    }

    public String getCourse_abstract() {
        return course_abstract;
    }

    public void setCourse_abstract(String course_abstract) {
        this.course_abstract = course_abstract;
    }

    public String getCourse_detail() {
        return course_detail;
    }

    public void setCourse_detail(String course_detail) {
        this.course_detail = course_detail;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getCourse_status() {
        return course_status;
    }

    public void setCourse_status(String course_status) {
        this.course_status = course_status;
    }

    public String getCourse_score() {
        return course_score;
    }

    public void setCourse_score(String course_score) {
        this.course_score = course_score;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    public String getTerm_id() {
        return term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getTerm_name() {
        return term_name;
    }

    public void setTerm_name(String term_name) {
        this.term_name = term_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
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

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public String getCourse_address() {
        return course_address;
    }

    public void setCourse_address(String course_address) {
        this.course_address = course_address;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "id='" + id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_stunum='" + course_stunum + '\'' +
                ", course_abstract='" + course_abstract + '\'' +
                ", course_detail='" + course_detail + '\'' +
                ", course_type='" + course_type + '\'' +
                ", course_status='" + course_status + '\'' +
                ", course_score='" + course_score + '\'' +
                ", school_id='" + school_id + '\'' +
                ", college_id='" + college_id + '\'' +
                ", term_id='" + term_id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", school_name='" + school_name + '\'' +
                ", college_name='" + college_name + '\'' +
                ", term_name='" + term_name + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                ", course_time='" + course_time + '\'' +
                ", course_address='" + course_address + '\'' +
                '}';
    }
}
