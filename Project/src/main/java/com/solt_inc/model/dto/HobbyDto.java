package com.solt_inc.model.dto;

import java.io.Serializable;

import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;

public class HobbyDto implements Serializable {

    private HobbyEntity hobbyEntity;
    private HobbyImageEntity hobbyImageEntity;

    public HobbyEntity getHobbyEntity() {
        return hobbyEntity;
    }

    public void setHobbyEntity(HobbyEntity hobbyEntity) {
        this.hobbyEntity = hobbyEntity;
    }

    public HobbyImageEntity getHobbyImageEntity() {
        return hobbyImageEntity;
    }

    public void setHobbyImageEntity(HobbyImageEntity hobbyImageEntity) {
        this.hobbyImageEntity = hobbyImageEntity;
    }
}
