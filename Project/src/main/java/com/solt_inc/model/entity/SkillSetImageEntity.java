package com.solt_inc.model.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class SkillSetImageEntity implements Serializable {

    private int id;
    private int SkillSetId;
    @NotNull
    private String imageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkillSetId() {
        return SkillSetId;
    }

    public void setSkillSetId(int SkillSetId) {
        this.SkillSetId = SkillSetId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
