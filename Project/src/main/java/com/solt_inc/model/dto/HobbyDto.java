package com.solt_inc.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.solt_inc.model.entity.HobbyEntity;
import com.solt_inc.model.entity.HobbyImageEntity;

public class HobbyDto implements Serializable {

    private HobbyEntity hobbyEntity;
    private List<HobbyImageEntity> hobbyImageEntityList = new ArrayList<HobbyImageEntity>();

    public HobbyEntity getHobbyEntity() {
        return hobbyEntity;
    }

    public void setHobbyEntity(HobbyEntity hobbyEntity) {
        this.hobbyEntity = hobbyEntity;
    }

    public List<HobbyImageEntity> getHobbyImageEntityList() {

        return hobbyImageEntityList;
    }

    public void setHobbyImageEntityList(List<HobbyImageEntity> hobbyImageEntityList) {
        this.hobbyImageEntityList = hobbyImageEntityList;
    }
}
