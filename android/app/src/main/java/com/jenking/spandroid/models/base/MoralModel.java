package com.jenking.spandroid.models.base;

public class MoralModel {
    public String id;
    public String moral_name;
    public String moral_abstract;
    public String moral_detail;
    public String moral_type;
    public String moral_status;
    public String moral_manager;
    public String moral_score;
    public String create_time;
    public String remark;
    public String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoral_name() {
        return moral_name;
    }

    public void setMoral_name(String moral_name) {
        this.moral_name = moral_name;
    }

    public String getMoral_abstract() {
        return moral_abstract;
    }

    public void setMoral_abstract(String moral_abstract) {
        this.moral_abstract = moral_abstract;
    }

    public String getMoral_detail() {
        return moral_detail;
    }

    public void setMoral_detail(String moral_detail) {
        this.moral_detail = moral_detail;
    }

    public String getMoral_type() {
        return moral_type;
    }

    public void setMoral_type(String moral_type) {
        this.moral_type = moral_type;
    }

    public String getMoral_status() {
        return moral_status;
    }

    public void setMoral_status(String moral_status) {
        this.moral_status = moral_status;
    }

    public String getMoral_manager() {
        return moral_manager;
    }

    public void setMoral_manager(String moral_manager) {
        this.moral_manager = moral_manager;
    }

    public String getMoral_score() {
        return moral_score;
    }

    public void setMoral_score(String moral_score) {
        this.moral_score = moral_score;
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
        return "MoralModel{" +
                "id='" + id + '\'' +
                ", moral_name='" + moral_name + '\'' +
                ", moral_abstract='" + moral_abstract + '\'' +
                ", moral_detail='" + moral_detail + '\'' +
                ", moral_type='" + moral_type + '\'' +
                ", moral_status='" + moral_status + '\'' +
                ", moral_manager='" + moral_manager + '\'' +
                ", moral_score='" + moral_score + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
