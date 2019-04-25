package com.example.listapp;

import java.util.Calendar;

public class TODOTask {
    //private static int lastId;
    //private int id;
    private String key;
    private String description;
    private Boolean completed;
    private long createdDate;
    //private String title;

    //public TODOTask() {}

    public TODOTask(String description) {
        //id = lastId++;
        this.description = description;
        completed = false;
        createdDate = Calendar.getInstance().getTime().getTime();
    }

    /*public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }*/

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getCreatedDate() {
        return createdDate;
    }
}
