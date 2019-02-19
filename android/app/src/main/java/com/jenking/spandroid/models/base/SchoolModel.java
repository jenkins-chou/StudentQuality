package com.jenking.spandroid.models.base;

public class SchoolModel {
    public String id;
    public String school_name;
    public String school_abstract;
    public String school_detail;
    public String school_number;
    public String school_address;
    public String school_build_time;
    public String create_time;
    public String remark;
    public String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_abstract() {
        return school_abstract;
    }

    public void setSchool_abstract(String school_abstract) {
        this.school_abstract = school_abstract;
    }

    public String getSchool_detail() {
        return school_detail;
    }

    public void setSchool_detail(String school_detail) {
        this.school_detail = school_detail;
    }

    public String getSchool_number() {
        return school_number;
    }

    public void setSchool_number(String school_number) {
        this.school_number = school_number;
    }

    public String getSchool_address() {
        return school_address;
    }

    public void setSchool_address(String school_address) {
        this.school_address = school_address;
    }

    public String getSchool_build_time() {
        return school_build_time;
    }

    public void setSchool_build_time(String school_build_time) {
        this.school_build_time = school_build_time;
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
        return "SchoolModel{" +
                "id='" + id + '\'' +
                ", school_name='" + school_name + '\'' +
                ", school_abstract='" + school_abstract + '\'' +
                ", school_detail='" + school_detail + '\'' +
                ", school_number='" + school_number + '\'' +
                ", school_address='" + school_address + '\'' +
                ", school_build_time='" + school_build_time + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
