package com.jenking.spandroid.models.base;

public class UserCertificateModel {
    public String id;
    public String certificate_id;
    public String get_certificate_time;
    public String user_certificate_status;
    public String create_time;
    public String remark;
    public String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(String certificate_id) {
        this.certificate_id = certificate_id;
    }

    public String getGet_certificate_time() {
        return get_certificate_time;
    }

    public void setGet_certificate_time(String get_certificate_time) {
        this.get_certificate_time = get_certificate_time;
    }

    public String getUser_certificate_status() {
        return user_certificate_status;
    }

    public void setUser_certificate_status(String user_certificate_status) {
        this.user_certificate_status = user_certificate_status;
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
        return "UserCertificateModel{" +
                "id='" + id + '\'' +
                ", certificate_id='" + certificate_id + '\'' +
                ", get_certificate_time='" + get_certificate_time + '\'' +
                ", user_certificate_status='" + user_certificate_status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
