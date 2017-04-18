package com.solt_inc.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.validation.constraints.NotNull;

public class FollowEntity implements Serializable {

    private int id;
    @NotNull
    private int userId;
    @NotNull
    private int followId;

    public FollowEntity() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowId() {
        return this.followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }
}
