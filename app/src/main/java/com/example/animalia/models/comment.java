package com.example.animalia.models;


import android.content.Context;

public class comment {
    private String comment,timestamp,name;

    public comment() {
    }

    public comment( String comment, String timestamp,String name) {

        this.comment = comment;
        this.timestamp = timestamp;
        this.name = name;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getName() {
        return name;
    }

    public void setName(String uName) {
        this.name = uName;
    }
}
