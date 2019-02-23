package com.jenking.spandroid.models.impl;

import com.jenking.spandroid.models.base.UserCertificateModel;

public class UserCertDetail extends UserCertificateModel {
    private String certificate_name;
    private String certificate_score;

    public String getCertificate_name() {
        return certificate_name;
    }

    public void setCertificate_name(String certificate_name) {
        this.certificate_name = certificate_name;
    }

    public String getCertificate_score() {
        return certificate_score;
    }

    public void setCertificate_score(String certificate_score) {
        this.certificate_score = certificate_score;
    }

    @Override
    public String toString() {
        return "UserCertDetail{" +
                "certificate_name='" + certificate_name + '\'' +
                ", certificate_score='" + certificate_score + '\'' +
                '}';
    }
}
