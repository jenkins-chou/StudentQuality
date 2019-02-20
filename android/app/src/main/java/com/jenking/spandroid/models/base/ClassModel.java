package com.jenking.spandroid.models.base;

public class ClassModel {
    public String id;
    public String class_name;
    public String class_number;
    public String class_abstract;
    public String class_detail;
    public String headmaster;
    public String school_id;
    public String college_id;
    public String school_name;
    public String college_name;
    public String create_time;
    public String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_number() {
        return class_number;
    }

    public void setClass_number(String class_number) {
        this.class_number = class_number;
    }

    public String getClass_abstract() {
        return class_abstract;
    }

    public void setClass_abstract(String class_abstract) {
        this.class_abstract = class_abstract;
    }

    public String getClass_detail() {
        return class_detail;
    }

    public void setClass_detail(String class_detail) {
        this.class_detail = class_detail;
    }

    public String getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(String headmaster) {
        this.headmaster = headmaster;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "ClassModel{" +
                "id='" + id + '\'' +
                ", class_name='" + class_name + '\'' +
                ", class_number='" + class_number + '\'' +
                ", class_abstract='" + class_abstract + '\'' +
                ", class_detail='" + class_detail + '\'' +
                ", headmaster='" + headmaster + '\'' +
                ", school_id='" + school_id + '\'' +
                ", college_id='" + college_id + '\'' +
                ", school_name='" + school_name + '\'' +
                ", college_name='" + college_name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
