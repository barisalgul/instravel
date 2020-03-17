package com.barisalgul.instravel.model;


import com.google.firebase.Timestamp;

public class PostModel {
    private String userEMail, comment, downloadUrl;
    private Timestamp date;

    public PostModel() {
    }

    public PostModel(String userEMail, String comment, String downloadUrl, Timestamp date) {
        this.userEMail = userEMail;
        this.comment = comment;
        this.downloadUrl = downloadUrl;
        this.date = date;
    }

    public String getUserEMail() {
        return userEMail;
    }

    public void setUserEMail(String userEMail) {
        this.userEMail = userEMail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
