package com.jenking.spandroid.models.base;

public class CertificateModel {
    public String id;
    public String certificate_name;
    public String certificate_sponsor;
    public String certificate_abstract;
    public String certificate_detail;
    public String certificate_score;
    public String certificate_manager;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertificate_name() {
        return certificate_name;
    }

    public void setCertificate_name(String certificate_name) {
        this.certificate_name = certificate_name;
    }

    public String getCertificate_sponsor() {
        return certificate_sponsor;
    }

    public void setCertificate_sponsor(String certificate_sponsor) {
        this.certificate_sponsor = certificate_sponsor;
    }

    public String getCertificate_abstract() {
        return certificate_abstract;
    }

    public void setCertificate_abstract(String certificate_abstract) {
        this.certificate_abstract = certificate_abstract;
    }

    public String getCertificate_detail() {
        return certificate_detail;
    }

    public void setCertificate_detail(String certificate_detail) {
        this.certificate_detail = certificate_detail;
    }

    public String getCertificate_score() {
        return certificate_score;
    }

    public void setCertificate_score(String certificate_score) {
        this.certificate_score = certificate_score;
    }

    public String getCertificate_manager() {
        return certificate_manager;
    }

    public void setCertificate_manager(String certificate_manager) {
        this.certificate_manager = certificate_manager;
    }

    @Override
    public String toString() {
        return "CertificateModel{" +
                "id='" + id + '\'' +
                ", certificate_name='" + certificate_name + '\'' +
                ", certificate_sponsor='" + certificate_sponsor + '\'' +
                ", certificate_abstract='" + certificate_abstract + '\'' +
                ", certificate_detail='" + certificate_detail + '\'' +
                ", certificate_score='" + certificate_score + '\'' +
                ", certificate_manager='" + certificate_manager + '\'' +
                '}';
    }
}
