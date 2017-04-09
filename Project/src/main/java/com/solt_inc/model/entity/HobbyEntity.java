package com.solt_inc.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class HobbyEntity implements Serializable {

    private int id;
    private int userId;
    @NotNull
    private String hobbyName;
    private String hobbyIcon;
    private String description;

    public String getHobbyIcon() {
        return hobbyIcon;
    }

    public void setHobbyIcon(String hobbyIcon) {
        this.hobbyIcon = hobbyIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}