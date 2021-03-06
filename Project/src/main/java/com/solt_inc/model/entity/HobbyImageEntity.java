package com.solt_inc.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class HobbyImageEntity implements Serializable {

    private int id;
    private int hobbyId;
    @NotNull
    private String imageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(int hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
